package Capstone.Petfinity.service.user;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.domain.Pet;
import Capstone.Petfinity.dto.info.hospital.HospitalListReqDto;
import Capstone.Petfinity.dto.info.hospital.InfoHospitalReqDto;
import Capstone.Petfinity.dto.info.parent.InfoParentReqDto;
import Capstone.Petfinity.dto.signup.parent.IdCheckReqDto;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.exception.InvalidNameException;
import Capstone.Petfinity.exception.InvalidPhoneNumberException;
import Capstone.Petfinity.exception.InvalidPwException;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.service.AesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.StringUtils.containsWhitespace;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ParentService {

    private final ParentRepository parentRepository;

    private final AesService aesService;

    @Transactional
    public void signup(SignupParentReqDto parent) {

        validateParent(parent); // 형식 확인
        duplicateParent(parent); // 중복 확인
        nullParent(parent); // null 확인
        parentRepository.save(parent);
        log.info("회원가입 성공");
    }

    public void idCheck(IdCheckReqDto parent) {

        idCheckParent(parent);
        log.info("아이디 중복 확인 성공");
    }

    public Parent infoParent(InfoParentReqDto parent) {

        Parent findParent;

        if (parent.getUuid().isEmpty()) {
            log.warn("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(parent.getUuid()) || parent.getUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (parentRepository.findOneByUuid(parent.getUuid()) == null) {
            log.warn("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }

        findParent = parentRepository.findOneByUuid(parent.getUuid());
        loginStatusParent(findParent);
        log.info("로그인 상태 확인 성공");

        return findParent;
    }

    public List<Hospital> hospitalList(HospitalListReqDto parent) {

        Parent findParent;
        List<Hospital> hospitalList;

        if (parent.getUuid().isEmpty()) {
            log.warn("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(parent.getUuid()) || parent.getUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (parentRepository.findOneByUuid(parent.getUuid()) == null) {
            log.warn("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }

        findParent = parentRepository.findOneByUuid(parent.getUuid());
        hospitalList =  parentRepository.findByAddress(findParent);
        log.info("병원 정보 조회 성공");

        return hospitalList;
    }

    private void nullParent(SignupParentReqDto parent) {

        if (parent.getName().isEmpty()) {
            log.warn("이름이 비어있습니다");
            throw new NullNameException();
        }
        if (parent.getPw().isEmpty()) {
            log.warn("비밀번호가 비어있습니다");
            throw new NullPwException();
        }
        if (parent.getCity().isEmpty()) {
            log.warn("도시가 비어있습니다");
            throw new NullCityException();
        }
        if(parent.getPhone_number().isBlank()) {
            log.warn("전화번호가 비어있습니다");
            throw new NullPhoneNumberException();
        }
    }

    private void validateParent(SignupParentReqDto parent) {

        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.warn("유효하지 않은 아이디입니다");
            throw new InvalidIdException();
        }
        if (parent.getPhone_number().length() != 11 || !parent.getPhone_number().matches("^[0-9]+$")) {
            log.warn("유효하지 않은 전화번호입니다.");
            throw new InvalidPhoneNumberException();
        }
        if (!parent.getPw().matches("^[a-zA-Z0-9]+$")) {
            log.warn("유효하지 않은 비밀번호입니다.");
            throw new InvalidPwException();
        }
        if (containsWhitespace(parent.getName())) {
            log.warn("유효하지 않은 이름입니다.");
            throw new InvalidNameException();
        }
        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.warn("유효하지 않은 아이디입니다");
            throw new InvalidIdException();
        }
    }

    private void duplicateParent(SignupParentReqDto parent) {

        if (!parentRepository.findById(parent.getId()).isEmpty()) {
            log.warn("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
        List<Parent> findParentsId = parentRepository.findById(parent.getId());
        if (!findParentsId.isEmpty()) {
            log.warn("이미 존재하는 회원입니다");
            throw new DuplicateIdException();
        }
        List<Parent> findParentsPhoneNumber = parentRepository.findByPhoneNumber(parent.getPhone_number());
        if (!findParentsPhoneNumber.isEmpty()) {
            log.warn("이미 존재하는 번호입니다");
            throw new DuplicatePhoneNumberException();
        }
    }

    private void idCheckParent(IdCheckReqDto parent) {

        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$")) {
            log.warn("유효하지 않은 아이디입니다");
            throw new InvalidIdException();
        }
        if (!parentRepository.findById(parent.getId()).isEmpty()) {
            log.warn("이미 존재하는 아이디입니다");
            throw new DuplicateIdException();
        }
    }

    private void loginStatusParent(Parent parent) {

        if (!parent.getLogin_status()) {
            log.warn("로그인 상태가 아닙니다");
            throw new NotLoginStatusException();
        }
    }
}