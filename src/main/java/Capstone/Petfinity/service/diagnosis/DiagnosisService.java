package Capstone.Petfinity.service.diagnosis;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.diagnosis.DiagnosisListDto;
import Capstone.Petfinity.dto.diagnosis.DiagnosisListReqDto;
import Capstone.Petfinity.dto.diagnosis.DiagnosisListResDto;
import Capstone.Petfinity.dto.diagnosis.SaveDiagnosisReqDto;
import Capstone.Petfinity.dto.loginout.LoginReqDto;
import Capstone.Petfinity.exception.LoginStatusException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.repository.DiagnosisRepository;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DiagnosisService {

    private final ParentRepository parentRepository;
    private final VetRepository vetRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Transactional
    public void saveDiagnosis(SaveDiagnosisReqDto diagnosis) {

        String userUuid = diagnosis.getUserUuid();

        userExistCheck(userUuid);
        checkLoginStatus(userUuid);
        diagnosisRepository.save(diagnosis);
        log.info("진단결과 저장 성공");
    }

    private void userExistCheck(String uuid) {

        Parent findParent = parentRepository.findOneByUuid(uuid);
        Vet findVet = vetRepository.findOneByUuid(uuid);

        if (findParent == null && findVet == null){

            log.warn("회원이 존재하지 않습니다");
            throw new NotExistException();
        }
    }

    private void checkLoginStatus(String uuid){

        Parent findParent = parentRepository.findOneByUuid(uuid);
        Vet findVet = vetRepository.findOneByUuid(uuid);

        boolean petLoginStatus;
        boolean vetLoginStatus;

        if (findParent != null) {
            petLoginStatus = parentRepository.checkLoginStatus(findParent);

            if (!petLoginStatus) {
                log.warn("로그아웃 상태입니다");
                throw new LoginStatusException();
            }
        }
        if (findVet != null) {
            vetLoginStatus = vetRepository.checkLoginStatus(findVet);

            if (!vetLoginStatus) {
                log.warn("로그아웃 상태입니다");
                throw new LoginStatusException();
            }
        }
    }

    public List<DiagnosisListDto> diagnosisList(DiagnosisListReqDto request){
        String userUuid = request.getUserUuid();

        userExistCheck(userUuid);
        checkLoginStatus(userUuid);

        List<DiagnosisListDto> result = diagnosisRepository.findDiagnoses(request.getUserUuid());

        log.info("진단리스트 확인 성공");
        return result;
    }
}
