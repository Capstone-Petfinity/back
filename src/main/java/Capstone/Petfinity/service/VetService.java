package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.info.vet.InfoVetReqDto;
import Capstone.Petfinity.dto.signup.parent.IdCheckReqDto;
import Capstone.Petfinity.dto.signup.vet.SignupVetReqDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.util.StringUtils.containsWhitespace;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class VetService {

    private final VetRepository vetRepository;

    @Transactional
    // 회원 가입
    public void signup(SignupVetReqDto vet) {

        validateVet(vet); // 형식 확인
        duplicateVet(vet); // 중복 확인
        nullVet(vet); // null 확인
        vetRepository.save(vet);
        log.info("회원가입 성공");
    }

    public void idCheck(IdCheckReqDto vet) {

        idCheckVet(vet);
        log.info("아이디 중복 확인 성공");
    }

    public Vet infoVet(InfoVetReqDto vet) {

        Vet findVet;

        if (vet.getUuid().isEmpty()) {
            log.warn("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(vet.getUuid()) || vet.getUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (vetRepository.findOneByUuid(vet.getUuid()) == null) {
            log.warn("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }

        findVet = vetRepository.findOneByUuid(vet.getUuid());
        loginStatusVet(findVet);
        log.info("로그인 상태 확인 성공");

        return findVet;
    }

    private void nullVet(SignupVetReqDto vet) {

        if (vet.getName().isBlank()) {
            log.warn("이름이 비어있습니다");
            throw new NullNameException();
        }
        if (vet.getPw().isBlank()) {
            log.warn("비밀번호가 비어있습니다");
            throw new NullPwException();
        }
    }

    private void validateVet(SignupVetReqDto vet) {

        if (vet.getId().length() != 5 || !vet.getId().matches("^[0-9]+$")) {
            log.warn("유효하지 않은 아이디입니다");
            throw new InvalidIdException();
        }
        if (!vet.getPw().matches("^[a-zA-Z0-9]+$")) {
            log.warn("유효하지 않은 비밀번호입니다.");
            throw new InvalidPwException();
        }
        if (StringUtils.containsWhitespace(vet.getName())) {
            log.warn("유효하지 않은 이름입니다.");
            throw new InvalidNameException();
        }
    }

    private void duplicateVet(SignupVetReqDto vet) {

        List<Vet> findPVetsId = vetRepository.findById(vet.getId());
        if (!findPVetsId.isEmpty()) {
            log.warn("이미 존재하는 회원입니다");
            throw new DuplicateIdException();
        }
    }

    private void idCheckVet(IdCheckReqDto vet) {

        if (vet.getId().length() != 5 || !vet.getId().matches("^[0-9]+$")) {
            log.warn("유효하지 않은 아이디입니다");
            throw new InvalidIdException();
        }
        if (!vetRepository.findById(vet.getId()).isEmpty()) {
            log.warn("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
    }

    private void loginStatusVet(Vet vet) {

        if (!vet.getLogin_status()) {
            log.warn("로그인 상태가 아닙니다");
            throw new NotLoginStatusException();
        }
    }
}
