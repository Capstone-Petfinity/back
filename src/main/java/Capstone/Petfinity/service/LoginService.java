package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.login.LoginReqDto;
import Capstone.Petfinity.exception.login.IncorrectPwException;
import Capstone.Petfinity.exception.login.NotExistException;
import Capstone.Petfinity.exception.login.NullIdException;
import Capstone.Petfinity.exception.signup.NullPwException;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final ParentRepository parentRepository;
    private final VetRepository vetRepository;

    private String uuid;

    public String login(LoginReqDto login) {
        nullLogin(login); // null 확인
        boolean result = isNumeric(login);
        if (result) { // 수의사
            vetExistCheck(login); // db 존재하는지 확인
            vetCorrectPw(login); // 비밀번호가 일치하는지 확인
            uuid = vetRepository.findOneById(login.getId()).getUuid();
        } else { // 보호자
            parentExistCheck(login); // db에 존재하는지 확인
            parentCorrectPw(login);// 비밀번호가 일치하는지 확인
            uuid = parentRepository.findOneById(login.getId()).getUuid();
        }
        log.info("로그인 성공");
        return uuid;
    }

    private void nullLogin(LoginReqDto login) {
        if (login.getId().isEmpty()) {
            log.error("아이디를 입력하지 않았습니다.");
            throw new NullIdException();
        }
        if (login.getPw().isEmpty()) {
            log.error("비밀번호를 입력하지 않았습니다.");
            throw new NullPwException();
        }
    }

    private void parentExistCheck(LoginReqDto login) {
        Parent findParentId = null;
        findParentId = parentRepository.findOneById(login.getId());
        if (findParentId == null) {
            log.error("해당 아이디가 존재하지 않습니다.");
            throw new NotExistException();
        }
    }

    private void vetExistCheck(LoginReqDto login) {
        Vet findVetId = null;
        findVetId = vetRepository.findOneById(login.getId());
        if (findVetId == null) {
            log.error("해당 아이디가 존재하지 않습니다.");
            throw new NotExistException();
        }
    }

    private void parentCorrectPw(LoginReqDto login) {
        Parent findParentId = parentRepository.findOneById(login.getId());
        if (!login.getPw().equals(findParentId.getPw())) {
            throw new IncorrectPwException();
        }
    }

    private void vetCorrectPw(LoginReqDto login) {
        Vet findVetId = vetRepository.findOneById(login.getId());
        if (!login.getPw().equals(findVetId.getPw())) {
            throw new IncorrectPwException();
        }
    }

    private boolean isNumeric(LoginReqDto login) {
        boolean isNumeric = login.getId().matches("[+-]?\\d*(\\.\\d+)?");
        return isNumeric;
    }

    public String parentOrVet(String uuid){
        Parent findParent = parentRepository.findOneByUuid(uuid);
        if(findParent != null){
            return "Parent";
        }
        else{
            return "Vet";
        }
    }
}




// 수의사, 보호자 구분
// 각각의 db에 접근
// 로그인 성공 여부 return
