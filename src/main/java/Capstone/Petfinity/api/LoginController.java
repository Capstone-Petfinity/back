package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.login.LoginRequestDto;
import Capstone.Petfinity.dto.login.LoginResponseDto;
import Capstone.Petfinity.dto.parent.SignupParentResponseDto;
import Capstone.Petfinity.service.LoginService;
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

    private final LoginService loginService;
    LoginResponseDto result;
    @PostMapping("/user/login")
    public LoginResponseDto Login(@RequestHeader("auth") String auth,
                                  @RequestBody LoginRequestDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new LoginResponseDto("400", "권한이 없습니다.");
            return result;
        }

        try {
            log.info("Start login");
            String uuid = loginService.login(request);

        }
    }
}
// service return을 uuid로
// service에서 db 훑는 함수를 하나더 만들고 controller에서 부르기