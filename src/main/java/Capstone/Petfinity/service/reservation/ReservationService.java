package Capstone.Petfinity.service.reservation;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.dto.info.hospital.InfoHospitalReqDto;
import Capstone.Petfinity.dto.reservation.ReservationReqDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.StringUtils.containsWhitespace;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ParentRepository parentRepository;

    @Transactional
    public void reservation(ReservationReqDto request) {

        nullReservationDate(request); // reservation_date null 확인
        checkParent(request); // parent uuid null, validate, db존재여부, loginstatus 확인
        checkHospital(request.getHospitalUuid()); // hospital uuid null, validate, db존재여부 확인
        checkPet(request); // pet uuid null, validate, db존재여부 확인
        reservationRepository.save(request);
        log.info("예약 성공");
    }

    public Hospital infoHospital(InfoHospitalReqDto request) {

        String hospitalUuid = request.getUuid();

        checkHospital(hospitalUuid);

        return reservationRepository.findHospital(hospitalUuid);
    }

    private void nullReservationDate(ReservationReqDto request) {

        if(request.getReservationDate().isEmpty()) {
            log.warn("예약날짜가 비어있습니다");
            throw new NullReservationDateException();
        }
    }

    private void checkParent(ReservationReqDto request) {

        if(request.getParentUuid().isEmpty()) {
            log.warn("parentUuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(request.getParentUuid()) || request.getParentUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (parentRepository.findOneByUuid(request.getParentUuid()) == null) {
            log.warn("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }
        Parent findParent = parentRepository.findOneByUuid(request.getParentUuid());
        if (!parentRepository.checkLoginStatus(findParent)){
            log.info("로그인 상태가 아닙니다");
            throw new NotLoginStatusException();
        }
    }

    private void checkHospital(String uuid) {

        if(uuid.isEmpty()) {
            log.warn("hospitalUuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(uuid) || uuid.length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (reservationRepository.findHospital(uuid) == null) {
            log.warn("해당 병원이 존재하지 않습니다");
            throw new NotExistException();
        }
    }

    private void checkPet(ReservationReqDto request) {

        if(request.getPetUuid().isEmpty()) {
            log.warn("petUuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(request.getPetUuid()) || request.getPetUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (reservationRepository.findPet(request.getPetUuid()) == null) {
            log.warn("반려동물이 존재하지 않습니다");
            throw new NotExistException();
        }
    }
}
