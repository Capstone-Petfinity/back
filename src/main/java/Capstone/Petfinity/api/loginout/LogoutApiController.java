package Capstone.Petfinity.api.loginout;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.loginout.LogoutReqDto;
import Capstone.Petfinity.exception.FailLogoutException;
import Capstone.Petfinity.service.loginout.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LogoutApiController {

    @Autowired
    private final LogoutService logoutService;

    @GetMapping("/user/logout")
    public NormalResDto logout(@RequestHeader("auth") String auth,
                               @RequestBody LogoutReqDto request) {

        NormalResDto result;

        log.debug("Auth Check");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.error("No Authorization");
            result = new NormalResDto("400", "권한 없음");
            return result;
        }

        try {
            log.debug("Start Logout");
            logoutService.logout(request);

            log.debug("Logout Success");
            result = new NormalResDto("200", "로그아웃 성공");
            return result;
        } catch (FailLogoutException e) {
            result = new NormalResDto("404", "로그아웃 실패");
            return result;
        }
    }


}
