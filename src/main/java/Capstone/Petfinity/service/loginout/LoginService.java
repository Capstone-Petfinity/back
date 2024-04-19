package Capstone.Petfinity.service.loginout;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.loginout.LoginReqDto;
import Capstone.Petfinity.exception.*;
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
            checkVetLoginStatus(request); // 로그인 상태 확인
            uuid = vetRepository.findOneById(request.getId()).getUuid();
            Vet vet = vetRepository.findOneByUuid(uuid);
            vetRepository.changeLoginStatus(vet);

            log.info("수의사 로그인 성공");
            return uuid;
        } else { // 보호자

            parentExistCheck(request); // db에 존재하는지 확인
            parentCorrectPw(request);// 비밀번호가 일치하는지 확인
            checkParentLoginStatus(request); // 로그인 상태 확인
            uuid = parentRepository.findOneById(request.getId()).getUuid();
            Parent parent = parentRepository.findOneByUuid(uuid);
            parentRepository.changeLoginStatus(parent);

            log.info("보호자 로그인 성공");
            return uuid;
        }
    }

    private void nullLogin(LoginReqDto request) {

        if (request.getId().isEmpty()) {

            log.warn("아이디를 입력하지 않았습니다");
            throw new NullIdException();
        }
        if (request.getPw().isEmpty()) {

            log.warn("비밀번호를 입력하지 않았습니다");
            throw new NullPwException();
        }
    }

    private void parentExistCheck(LoginReqDto request) {

        List<Parent> findParent = parentRepository.findById(request.getId());
        if (findParent.isEmpty()){

            log.warn("[보호자] 해당 아이디가 존재하지 않습니다");
            throw new NotExistException();
        }
//        try {
//
//          parentRepository.findOneById(request.getId());
//        } catch (NoResultException e) {
//
//            throw new NotExistException();
//        }

//        if (parentRepository.findOneById(request.getId()) == null) {
//            log.warn("[보호자] 해당 아이디가 존재하지 않습니다.");
//        }
//        if (parentRepository.findById(request.getId()).isEmpty()) {   // [ ]list로 받아서 해결됐어 그냥 하나 찾는거랑
//            log.warn("[보호자] 해당 아이디가 존재하지 않습니다."); // list랑 무슨 차이일까?
//            throw new NotExistException();
//
//        }
    }

    private void vetExistCheck (LoginReqDto request){

        List<Vet> findVet = vetRepository.findById(request.getId());
        if (findVet.isEmpty()){

            log.warn("[수의사] 해당 아이디가 존재하지 않습니다");
            throw new NotExistException();
        }
//        if (vetRepository.findById(request.getId()).isEmpty()) {
//
//            log.warn("[수의사] 해당 아이디가 존재하지 않습니다.");
//            throw new NotExistException();
//        }
    }

    private void parentCorrectPw (LoginReqDto request) {

        Parent findParent = parentRepository.findOneById(request.getId());

        if (!request.getPw().equals(findParent.getPw())) {

            log.warn("[보호자] 일치하지 않는 비밀번호입니다");
            throw new IncorrectPwException();
//            findParent = parentRepository.findOneById(request.getId());   // [ ]findParentId가 null이라고 뜸 왜지 왜..
//            if (!request.getPw().equals(findParent.getPw())) {
//                //존재하는 아이디인데도.. 왜..  -> find는 uuid로밖에 못찾고, findOneById는 꼭 있어야함
//                throw new IncorrectPwException();
//            }
        }
    }
    private void vetCorrectPw (LoginReqDto request) {

        Vet findVet = vetRepository.findOneById(request.getId());

        if (!request.getPw().equals(findVet.getPw())) {

            log.warn("[수의사] 일치하지 않는 비밀번호입니다");
            throw new IncorrectPwException();
        }
    }

    private void checkParentLoginStatus(LoginReqDto request){

        Parent findParent = parentRepository.findOneById(request.getId());
        boolean loginStatus = parentRepository.checkLoginStatus(findParent);
        if (loginStatus){

            log.warn("[보호자] 이미 로그인 된 상태입니다");
            throw new LoginStatusException();
        }
    }

    private void checkVetLoginStatus(LoginReqDto request){
        Vet findVet = vetRepository.findOneById(request.getId());
        boolean loginStatus = vetRepository.checkLoginStatus(findVet);
        if (loginStatus){

            log.warn("[수의사] 이미 로그인 된 상태입니다");
            throw new LoginStatusException();
        }
    }

    private boolean isNumeric (LoginReqDto request){

        return request.getId().matches("[+-]?\\d*(\\.\\d+)?"); // 이거 무슨 의미인지 주석 부탁함둥
    }

    public Boolean isParent(LoginReqDto request) {

        List<Parent> findParent = parentRepository.findById(request.getId());

        return !findParent.isEmpty();
    }
}




// 수의사, 보호자 구분 [v]
// 각각의 db에 접근 [v]
// 로그인 성공 여부 return [v]
