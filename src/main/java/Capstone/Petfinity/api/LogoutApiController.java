package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.logout.LogoutReqDto;
import Capstone.Petfinity.dto.logout.LogoutResDto;
import Capstone.Petfinity.exception.logout.FailLogoutException;
import Capstone.Petfinity.service.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LogoutApiController {

    @Autowired
    LogoutService logoutService;

    LogoutResDto result;

    @GetMapping("/user/logout")
    public LogoutResDto logout(@RequestHeader("auth") String auth,
                               @RequestBody LogoutReqDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new LogoutResDto("400", "권한이 없습니다");
            return result;
        }

        try {
            log.info("Start Logout");
            logoutService.logout(request);

            log.info("로그아웃 성공");
            result = new LogoutResDto("200", "Logout Success");
            return result;
        } catch (FailLogoutException e) {
            log.error("로그아웃 실패");
            result = new LogoutResDto("404", "로그아웃 실패");
            return result;
        }
    }
}
