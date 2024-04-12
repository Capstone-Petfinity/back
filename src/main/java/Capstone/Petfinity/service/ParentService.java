package Capstone.Petfinity.service;

import Capstone.Petfinity.dto.parent.IdCheckRequestDto;
import Capstone.Petfinity.dto.parent.SignupParentRequestDto;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.exception.signup.*;
import Capstone.Petfinity.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ParentService {

    private final ParentRepository parentRepository;

    @Transactional
    // 회원 가입
    public void signup(SignupParentRequestDto parent) {
        validateParent(parent); // 형식 확인
        duplicateParent(parent); // 중복 확인
        nullParent(parent); // null 확인
        parentRepository.save(parent);
        log.info("회원가입 성공");
    }
    @Transactional
    public void idCheck(IdCheckRequestDto parent) {
        idCheckParent(parent);
        log.info("아이디 중복 확인");
    }

    private void nullParent(SignupParentRequestDto parent) {
        if (parent.getName().isEmpty()) {
            log.error("이름이 비어있습니다");
            throw new NullNameException();
        }
        if (parent.getPw().isEmpty()) {
            log.error("비밀번호가 비어있습니다");
            throw new NullPwException();
        }
        if (parent.getCity().isEmpty()) {
            log.error("도시가 비어있습니다");
            throw new NullCityException();
        }
    }

    private void validateParent(SignupParentRequestDto parent) {
        if (parent.getPhone_number().length() != 11 || !parent.getPhone_number().matches("^[0-9]+$")) {
            log.error("유효하지 않는 전화번호입니다.");
            throw new InvalidPhoneNumberException();
        }
        if (!parent.getPw().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 비밀번호입니다.");
            throw new InvalidPwException();
        }
        if (StringUtils.containsWhitespace(parent.getName())) {
            log.error("유효하지 않는 이름입니다.");
            throw new InvalidNameException();
        }
    }

    private void duplicateParent(SignupParentRequestDto parent) {
        List<Parent> findParentsId = parentRepository.findById(parent.getId());
        if (!findParentsId.isEmpty()) {
            log.error("이미 존재하는 회원입니다");
            throw new DuplicateIdException();
        }
        List<Parent> findParentsPhoneNumber = parentRepository.findByPhoneNumber(parent.getPhone_number());
        if (!findParentsPhoneNumber.isEmpty()) {
            log.error("이미 존재하는 번호입니다");
            throw new DuplicatePhoneNumberException();
        }
    }

    private void idCheckParent(IdCheckRequestDto parent) {
        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
        if (!parentRepository.findById(parent.getId()).isEmpty()) {
            log.error("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
    }

        // 지영아 로그인 코드 여기 밑에다가 짜줭!
}
