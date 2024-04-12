package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.logout.LogoutRequestDto;
import Capstone.Petfinity.dto.logout.LogoutResponseDto;
import Capstone.Petfinity.dto.parent.SignupParentRequestDto;
import Capstone.Petfinity.dto.parent.SignupParentResponseDto;
import Capstone.Petfinity.exception.logout.InvalidStatusException;
import Capstone.Petfinity.service.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LogoutApiController {

    @Autowired
    LogoutService logoutService;

    LogoutResponseDto result;

    @GetMapping("/user/logout")
    public LogoutResponseDto logout(@RequestHeader("auth") String auth,
                                    @RequestBody LogoutRequestDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new LogoutResponseDto("400", "권한이 없습니다.");
            return result;
        }
        try {
            log.info("Start Logout");
            logoutService.logout(request);
            result = new LogoutResponseDto("200", "Logout Success");
            return result;
        } catch (InvalidStatusException e) {
            result = new LogoutResponseDto("404", "이미 로그아웃 상태입니다.");
            return result;
        }
    }
}
