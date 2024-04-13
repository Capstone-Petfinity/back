package Capstone.Petfinity.api.signup;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.signup.vet.SignupVetReqDto;
import Capstone.Petfinity.exception.NullNameException;
import Capstone.Petfinity.exception.NullPwException;
import Capstone.Petfinity.exception.DuplicateIdException;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.exception.InvalidNameException;
import Capstone.Petfinity.exception.InvalidPwException;
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
public class SignupVetApiController {

    @Autowired
    private final VetService vetService;

    NormalResDto result;

    @PostMapping("/user/signup/vet")
    public NormalResDto signupVet(@RequestHeader("auth") String auth,
                                     @RequestBody SignupVetReqDto request) {

        log.debug("Auth Check");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.error("No Authorization");
            result = new NormalResDto("400", "권한 없음");
            return result;
        }

        log.debug("Start Signup");
        try {
            vetService.signup(request);

            log.debug("Vet Signup Success");
            result = new NormalResDto("200", "수의사 회원가입 성공");
            return result;
        } catch (InvalidIdException e) {

            result = new NormalResDto("401", "유효하지 않는 아이디");
            return result;
        } catch (InvalidPwException e) {

            result = new NormalResDto("401", "유효하지 않는 비밀번호");
            return result;
        } catch (InvalidNameException e) {

            result = new NormalResDto("401", "유효하지 않는 이름");
            return result;
        } catch (DuplicateIdException e) {

            result = new NormalResDto("402", "중복된 아이디");
            return result;
        } catch (NullNameException e) {

            result = new NormalResDto("403", "입력되지 않은 이름");
            return result;
        } catch (NullPwException e) {

            result = new NormalResDto("403", "입력되지 않은 비밀번호");
            return result;
        }
    }
}