package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.vet.SignupVetReqDto;
import Capstone.Petfinity.dto.vet.SignupVetResDto;
import Capstone.Petfinity.exception.signup.*;
import Capstone.Petfinity.service.VetService;
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
public class VetApiController {

    @Autowired
    private final VetService vetService;

    SignupVetResDto result;

    @PostMapping("/user/signup/vet")
    public SignupVetResDto signupVet(@RequestHeader("auth") String auth,
                                     @RequestBody SignupVetReqDto request) {

        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new SignupVetResDto("400", "권한이 없습니다.");
            return result;
        }

        try {
            log.info("Start Signup");
            vetService.signup(request);
            result = new SignupVetResDto("200", "Sign Success");
            return result;
        } catch (InvalidIdException e) {
            result = new SignupVetResDto("401", "유효하지 않는 아이디");
            return result;
        } catch (InvalidPwException e) {
            result = new SignupVetResDto("401", "유효하지 않는 비밀번호");
            return result;
        } catch (InvalidNameException e) {
            result = new SignupVetResDto("401", "유효하지 않는 이름");
            return result;
        } catch (DuplicateIdException e) {
            result = new SignupVetResDto("402", "중복된 아이디");
            return result;
        } catch (NullNameException e) {
            result = new SignupVetResDto("403", "이름 공백");
            return result;
        } catch (NullPwException e) {
            result = new SignupVetResDto("403", "비밀번호 공백");
            return result;
        }
    }

    // 지영아 여기다가 로그인 코드 작성해조!
}