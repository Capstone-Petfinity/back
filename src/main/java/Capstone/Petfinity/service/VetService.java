package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.signup.parent.IdCheckReqDto;
import Capstone.Petfinity.dto.signup.vet.SignupVetReqDto;
import Capstone.Petfinity.exception.NullNameException;
import Capstone.Petfinity.exception.NullPwException;
import Capstone.Petfinity.exception.DuplicateIdException;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.exception.InvalidNameException;
import Capstone.Petfinity.exception.InvalidPwException;
import Capstone.Petfinity.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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
        log.debug("회원가입 성공");
    }

    public void idCheck(IdCheckReqDto vet) {

        idCheckVet(vet);
        log.debug("아이디 중복 확인 성공");
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
            log.warn("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
        if (!vet.getPw().matches("^[a-zA-Z0-9]+$")) {
            log.warn("유효하지 않는 비밀번호입니다.");
            throw new InvalidPwException();
        }
        if (StringUtils.containsWhitespace(vet.getName())) {
            log.warn("유효하지 않는 이름입니다.");
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
            log.warn("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
        if (!vetRepository.findById(vet.getId()).isEmpty()) {
            log.warn("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
    }
}
