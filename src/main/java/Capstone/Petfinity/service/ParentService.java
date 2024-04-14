package Capstone.Petfinity.service;

import Capstone.Petfinity.dto.info.InfoParentReqDto;
import Capstone.Petfinity.dto.signup.parent.IdCheckReqDto;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.exception.InvalidNameException;
import Capstone.Petfinity.exception.InvalidPhoneNumberException;
import Capstone.Petfinity.exception.InvalidPwException;
import Capstone.Petfinity.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.containsWhitespace;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ParentService {

    private final ParentRepository parentRepository;

    @Transactional
    public void signup(SignupParentReqDto parent) {

        validateParent(parent); // 형식 확인
        duplicateParent(parent); // 중복 확인
        nullParent(parent); // null 확인
        parentRepository.save(parent);
        log.debug("회원가입 성공");
    }

    public void idCheck(IdCheckReqDto parent) {

        idCheckParent(parent);
        log.debug("아이디 중복 확인 성공");
    }

    public Parent infoParent(InfoParentReqDto parent) {

        Parent findParent;

        if (parent.getUuid().isEmpty()) {
            log.error("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(parent.getUuid()) || parent.getUuid().length() != 36) {
            log.error("유효하지 않는 uuid입니다");
            throw new InvalidUuidException();
        }
        if (parentRepository.findOneByUuid(parent.getUuid()) == null) {
            log.error("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }

        findParent = parentRepository.findOneByUuid(parent.getUuid());
        loginStatusParent(findParent);
        log.debug("로그인 상태 확인 성공");

        return findParent;
    }

    private void nullParent(SignupParentReqDto parent) {

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

    private void validateParent(SignupParentReqDto parent) {

        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
        if (parent.getPhone_number().length() != 11 || !parent.getPhone_number().matches("^[0-9]+$")) {
            log.error("유효하지 않는 전화번호입니다.");
            throw new InvalidPhoneNumberException();
        }
        if (!parent.getPw().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 비밀번호입니다.");
            throw new InvalidPwException();
        }
        if (containsWhitespace(parent.getName())) {
            log.error("유효하지 않는 이름입니다.");
            throw new InvalidNameException();
        }
        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
    }

    private void duplicateParent(SignupParentReqDto parent) {

        if (!parentRepository.findById(parent.getId()).isEmpty()) {
            log.error("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
        Optional<Parent> findParentsId = parentRepository.findById(parent.getId());
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

    private void idCheckParent(IdCheckReqDto parent) {

        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.error("유효하지 않는 아이디입니다");
            throw new InvalidIdException();
        }
        if (!parentRepository.findById(parent.getId()).isEmpty()) {
            log.error("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
    }

    private void loginStatusParent(Parent parent) {

        if (!parent.getLogin_status()) {
            log.error("로그인 상태가 아닙니다");
            throw new NotLoginStatusException();
        }
    }
}