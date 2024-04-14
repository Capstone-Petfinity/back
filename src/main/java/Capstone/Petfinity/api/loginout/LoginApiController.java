package Capstone.Petfinity.api.loginout;

import Capstone.Petfinity.dto.loginout.LoginReqDto;
import Capstone.Petfinity.dto.loginout.LoginResDto;
import Capstone.Petfinity.exception.IncorrectPwException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NullIdException;
import Capstone.Petfinity.exception.NullPwException;
import Capstone.Petfinity.service.loginout.LoginService;
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

    @PostMapping("/user/login")
    public LoginResDto Login(@RequestHeader("auth") String auth,
                             @RequestBody LoginReqDto request) {

        LoginResDto result;

        log.debug("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            result = new LoginResDto(null, null, "400", "권한 없음");
            return result;
        }

        try {
            log.debug("로그인 시작");
            String uuid = loginService.login(request);
            Boolean isParent = loginService.isParent(uuid);

            log.debug("로그인 성공");
            result = new LoginResDto(uuid, isParent, "200", "로그인 성공");
            return result;
        } catch (NullIdException e){

            result = new LoginResDto(null, null, "401", "입력되지 않은 아이디");
            return result;
        } catch (NullPwException e){

            result = new LoginResDto(null, null, "401", "입력되지 않은 비밀번호");
            return result;
        } catch (NotExistException e){

            result = new LoginResDto(null, null, "404", "존재하지 않는 아이디");
            return result;
        } catch (IncorrectPwException e){

            result = new LoginResDto(null, null, "405", "일치하지 않는 비밀번호");
            return result;
        }
    }
}
// service return을 uuid로 [v]
// service에서 db 훑는 함수를 하나더 만들고 controller에서 부르기 [v]