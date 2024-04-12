package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.login.LoginReqDto;
import Capstone.Petfinity.dto.login.LoginResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    LoginResDto result;
    @PostMapping("/user/login")
    public LoginResDto Login(@RequestHeader("auth") String auth,
                             @RequestBody LoginReqDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            //result = new LoginResponseDto("400", "권한이 없습니다.");
            //return result;
        }

//        try {
//            log.info("Start login");
//            return null;
//        }
        return null;
    }
}
// service return을 uuid로
// service에서 db 훑는 함수를 하나더 만들고 controller에서 부르기