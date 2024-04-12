package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.vet.SignupVetRequestDto;
import Capstone.Petfinity.exception.signup.*;
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
    public void signup(SignupVetRequestDto vet) {
        validateParent(vet); // 형식 확인
        duplicateParent(vet); // 중복 확인
        nullParent(vet); // null 확인
        vetRepository.save(vet);
        log.info("회원가입 성공");
    }

    private void nullParent(SignupVetRequestDto vet) {
        if (vet.getName().isBlank()) {
            log.error("이름이 비어있습니다");
            throw new NullNameException();
        }
        if (vet.getPw().isBlank()) {
            log.error("비밀번호가 비어있습니다");
            throw new NullPwException();
        }
    }

    private void validateParent(SignupVetRequestDto vet) {
        if (vet.getId().length() != 5 || !vet.getId().matches("^[0-9]+$")) {
            log.error("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
        if (!vet.getPw().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 비밀번호입니다.");
            throw new InvalidPwException();
        }
        if (StringUtils.containsWhitespace(vet.getName())) {
            log.error("유효하지 않는 이름입니다.");
            throw new InvalidNameException();
        }
    }

    private void duplicateParent(SignupVetRequestDto vet) {
        List<Vet> findPVetsId = vetRepository.findById(vet.getId());
        if (!findPVetsId.isEmpty()) {
            log.error("이미 존재하는 회원입니다");
            throw new DuplicateIdException();
        }
    }

    public void changeLoginStatus(Vet vet) {

        if (!vet.getLogin_status()) {
            vet.setLogin_status(Boolean.TRUE);
        } else {
            vet.setLogin_status(Boolean.FALSE);
        }
    }
}
