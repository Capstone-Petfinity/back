package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.dto.login.LoginRequestDto;
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

    public void login(LoginRequestDto login){
        boolean result = isNumeric(login);
        if(result == true){ // 수의사
            Parent parent = parentRepository.findOne(login.getId());
            if(login.getPw().equals(parent.getPw())){

            }
        }
        else if(result == false){ // 보호자

        }
    }

    private boolean isNumeric(LoginRequestDto login){
        boolean isNumeric = login.getId().matches("[+-]?\\d*(\\.\\d+)?");
        return isNumeric;
    }


}


// 수의사, 보호자 구분
// 각각의 db에 접근
// 로그인 성공 여부 return