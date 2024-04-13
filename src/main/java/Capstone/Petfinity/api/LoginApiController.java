package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.login.LoginReqDto;
import Capstone.Petfinity.dto.login.LoginResDto;
import Capstone.Petfinity.exception.login.IncorrectPwException;
import Capstone.Petfinity.exception.login.NotExistException;
import Capstone.Petfinity.exception.login.NullIdException;
import Capstone.Petfinity.exception.signup.NullPwException;
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
public class LoginApiController {

    private final LoginService loginService;
    LoginResDto result;
    private String uuid;
    private Boolean isParent;

    @PostMapping("/user/login")
    public LoginResDto Login(@RequestHeader("auth") String auth,
                             @RequestBody LoginReqDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new LoginResDto(null, null, "400", "권한이 없습니다.");
            return result;
        }

        try {
            log.debug("Start login");
            uuid = loginService.login(request);
            isParent = loginService.isParent(uuid);
            result = new LoginResDto(uuid, isParent, "200", "Login Success");
            return result;
        } catch (NullIdException e){
            result = new LoginResDto(null, null, "401", "입력되지 않은 아이디");
            return result;
        } catch (NullPwException e){
            result = new LoginResDto(null, null, "401", "입력되지 않은 비밀번호");
            return result;
        } catch (NotExistException e){
            result = new LoginResDto(null, null, "402", "존재하지 않는 아이디");
            return result;
        } catch (IncorrectPwException e){
            result = new LoginResDto(null, null, "403", "일치하지 않는 비밀번호");
            return result;
        }

    }
}
// service return을 uuid로 [v]
// service에서 db 훑는 함수를 하나더 만들고 controller에서 부르기 [v]