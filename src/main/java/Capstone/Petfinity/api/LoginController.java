package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.login.LoginRequestDto;
import Capstone.Petfinity.dto.login.LoginResponseDto;
import Capstone.Petfinity.dto.parent.SignupParentResponseDto;
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
        }
    }
}
// service return을 uuid로