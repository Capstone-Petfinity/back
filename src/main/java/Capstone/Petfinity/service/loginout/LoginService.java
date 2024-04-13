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
            Vet vet = vetRepository.findOneByUuid(uuid);
            vetRepository.changeLoginStatus(vet);
            log.debug("로그인 성공");
            return uuid;
        } else { // 보호자
            parentExistCheck(request); // db에 존재하는지 확인
            parentCorrectPw(request);// 비밀번호가 일치하는지 확인
            uuid = parentRepository.findOneById(request.getId()).getUuid();
            Parent parent = parentRepository.findOneByUuid(uuid);
            parentRepository.changeLoginStatus(parent);
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

        if (parentRepository.findById(request.getId()).isEmpty()) {   // [ ]list로 받아서 해결됐어 그냥 하나 찾는거랑
            log.error("[보호자] 해당 아이디가 존재하지 않습니다."); // list랑 무슨 차이일까?

            if (parentRepository.findOneById(request.getId()) == null) {
                log.error("[보호자] 해당 아이디가 존재하지 않습니다.");
                throw new NotExistException();
            }
        }
    }

    private void vetExistCheck (LoginReqDto request){

        if (vetRepository.findById(request.getId()).isEmpty()) {
            log.error("[수의사] 해당 아이디가 존재하지 않습니다.");
            throw new NotExistException();
        }
    }

    private void parentCorrectPw (LoginReqDto request) {

        Parent findParentId = parentRepository.findOneById(request.getId());
        if (!request.getPw().equals(findParentId.getPw())) {
            findParentId = parentRepository.findOneById(request.getId());   // [ ]findParentId가 null이라고 뜸 왜지 왜..
            if (!request.getPw().equals(findParentId.getPw())) { //존재하는 아이디인데도.. 왜..
                throw new IncorrectPwException();
            }
        }
    }
    private void vetCorrectPw (LoginReqDto request) {

        Vet findVetId = vetRepository.findOneById(request.getId());
        if (!request.getPw().equals(findVetId.getPw())) {
            throw new IncorrectPwException();
        }
    }
    private boolean isNumeric (LoginReqDto request){

        return request.getId().matches("[+-]?\\d*(\\.\\d+)?");
    }

    public Boolean isParent(String uuid) {

        Parent findParent = parentRepository.findOneByUuid(uuid);
        return findParent != null;
    }
}




// 수의사, 보호자 구분
// 각각의 db에 접근
// 로그인 성공 여부 return
