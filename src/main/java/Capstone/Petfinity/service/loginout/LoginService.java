package Capstone.Petfinity.service.loginout;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.loginout.LoginReqDto;
import Capstone.Petfinity.exception.IncorrectPwException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NullIdException;
import Capstone.Petfinity.exception.NullPwException;
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

    @Transactional
    public String login(LoginReqDto request) {

        nullLogin(request); // null 확인
        boolean result = isNumeric(request);
        String uuid;

        if (result) { // 수의사
            vetExistCheck(request); // db 존재하는지 확인
            vetCorrectPw(request); // 비밀번호가 일치하는지 확인
            uuid = vetRepository.findOneById(request.getId()).getUuid();
            log.debug("로그인 성공");
            return uuid;
        } else { // 보호자
            parentExistCheck(request); // db에 존재하는지 확인
            parentCorrectPw(request);// 비밀번호가 일치하는지 확인
            uuid = parentRepository.findOneById(request.getId()).getUuid();
            log.debug("로그인 성공");
            return uuid;
        }
    }

    private void nullLogin(LoginReqDto request) {

        if (request.getId().isEmpty()) {
            log.error("아이디를 입력하지 않았습니다.");
            throw new NullIdException();
        }
        if (request.getPw().isEmpty()) {
            log.error("비밀번호를 입력하지 않았습니다.");
            throw new NullPwException();
        }
    }

    private void parentExistCheck(LoginReqDto request) {

        if (parentRepository.findOneById(request.getId()) == null) {
            log.error("[보호자] 해당 아이디가 존재하지 않습니다.");
            throw new NotExistException();
        }
    }

    private void vetExistCheck(LoginReqDto request) {

        if (vetRepository.findOneById(request.getId()) == null) {
            log.error("[수의사] 해당 아이디가 존재하지 않습니다.");
            throw new NotExistException();
        }
    }

    private void parentCorrectPw(LoginReqDto request) {

        Parent findParentId = parentRepository.findOneById(request.getId());
        if (!request.getPw().equals(findParentId.getPw())) {
            throw new IncorrectPwException();
        }
    }

    private void vetCorrectPw(LoginReqDto request) {

        Vet findVetId = vetRepository.findOneById(request.getId());
        if (!request.getPw().equals(findVetId.getPw())) {
            throw new IncorrectPwException();
        }
    }

    private boolean isNumeric(LoginReqDto request) {
        return request.getId().matches("[+-]?\\d*(\\.\\d+)?");
    }

    public Boolean isParent(String uuid){
        Parent findParent = parentRepository.findOneByUuid(uuid);
        return findParent != null;
    }
}




// 수의사, 보호자 구분
// 각각의 db에 접근
// 로그인 성공 여부 return
