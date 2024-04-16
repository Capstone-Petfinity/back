package Capstone.Petfinity.api.loginout;

import Capstone.Petfinity.dto.loginout.LoginReqDto;
import Capstone.Petfinity.dto.loginout.LoginResDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.service.loginout.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginApiController {

    @Autowired
    private final LoginService loginService;

    LoginResDto result;

    @PostMapping("/user/login")
    public LoginResDto Login(@RequestHeader("auth") String auth,
                             @RequestBody LoginReqDto request) {

        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            result = new LoginResDto("400", "권한 없음", null, null);
            return result;
        }

        try {
            log.info("로그인 시작");
            String uuid = loginService.login(request);
            Boolean isParent = loginService.isParent(request);

            log.info("로그인 성공");
            result = new LoginResDto("200", "로그인 성공", uuid, isParent);
            return result;
        } catch (NullIdException e){

            result = new LoginResDto("401", "입력되지 않은 아이디", null, null);
            return result;
        } catch (NullPwException e){

            result = new LoginResDto("401", "입력되지 않은 비밀번호", null, null);
            return result;
        } catch (NotExistException e){

            result = new LoginResDto("404", "존재하지 않는 아이디", null, null);
            return result;
        } catch (IncorrectPwException e){

            result = new LoginResDto("405", "일치하지 않는 비밀번호", null, null);
            return result;
        } catch (LoginStatusException e){

            result = new LoginResDto("406", "이미 로그인된 계정", null, null);
            return result;
        }
    }
}
// service return을 uuid로 [v]
// service에서 db 훑는 함수를 하나더 만들고 controller에서 부르기 [v]