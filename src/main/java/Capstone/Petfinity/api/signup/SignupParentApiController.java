package Capstone.Petfinity.api.signup;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.exception.InvalidNameException;
import Capstone.Petfinity.exception.InvalidPhoneNumberException;
import Capstone.Petfinity.exception.InvalidPwException;
import Capstone.Petfinity.service.AesService;
import Capstone.Petfinity.service.user.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SignupParentApiController {

    @Autowired
    private final ParentService parentService;
    @Autowired
    private final AesService aesService;

    @Value("${auth.key}")
    private String authKey;

    NormalResDto result;

    @PostMapping("/user/signup/parent")
    public NormalResDto signupParent(@RequestHeader("auth") String auth,
                               @RequestBody SignupParentReqDto request) {

        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            result = new NormalResDto(aesService.encryptAES("400"), aesService.encryptAES("권한 없음"));
            return result;
        }

        log.info("보호자 회원가입 시작");
        try {
            parentService.signup(request);

            log.info("보호자 회원가입 성공");
            result = new NormalResDto(aesService.encryptAES("200"), aesService.encryptAES("보호자 회원가입 성공"));
            return result;
        } catch (InvalidIdException e) {

            result = new NormalResDto("401", "유효하지 않은 아이디");
            return result;
        } catch (InvalidPwException e) {

            result = new NormalResDto("401", "유효하지 않은 비밀번호");
            return result;
        } catch (InvalidNameException e) {

            result = new NormalResDto("401", "유효하지 않은 이름");
            return result;
        } catch (InvalidPhoneNumberException e) {

            result = new NormalResDto("401", "유효하지 않은 전화번호");
            return result;
        } catch (DuplicateIdException e) {

            result = new NormalResDto("402", "중복된 아이디");
            return result;
        } catch (DuplicatePhoneNumberException e) {

            result = new NormalResDto("402", "중복된 전화번호");
            return result;
        } catch (NullNameException e) {

            result = new NormalResDto("403", "입력되지 않은 이름");
            return result;
        } catch (NullPwException e) {

            result = new NormalResDto("403", "입력되지 않은 비밀번호");
            return result;
        } catch (NullCityException e) {

            result = new NormalResDto("403", "입력되지 않은 도시");
            return result;
        }
    }
}
