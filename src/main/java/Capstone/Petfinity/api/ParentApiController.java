package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.parent.SignupParentRequestDto;
import Capstone.Petfinity.dto.parent.SignupParentResponseDto;
import Capstone.Petfinity.exception.signup.*;
import Capstone.Petfinity.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ParentApiController {

    @Autowired
    private final ParentService parentService;

    SignupParentResponseDto result;

    @PostMapping("/user/signup/parent")
    public SignupParentResponseDto signupParent(@RequestBody SignupParentRequestDto request) {

        if (!request.getAuth().equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new SignupParentResponseDto("401", "권한이 없습니다.");
            return result;
        }

        try {
            log.info("Start Signup");
            parentService.signup(request);
            result = new SignupParentResponseDto("200", "Login Success");
            return result;
        } catch (InvalidIdException e) {
            result = new SignupParentResponseDto("400", "유효하지 않는 아이디");
            return result;
        } catch (InvalidPhoneNumberException e) {
            result = new SignupParentResponseDto("400", "유효하지 않는 전화번호");
            return result;
        } catch (InvalidPwException e) {
            result = new SignupParentResponseDto("400", "유효하지 않는 비밀번호");
            return result;
        } catch (DuplicateIdException e) {
            result = new SignupParentResponseDto("400", "중복된 아이디");
            return result;
        } catch (DuplicatePhoneNumberException e) {
            result = new SignupParentResponseDto("400", "중복된 전화번호");
            return result;
        } catch (NullNameException e) {
            result = new SignupParentResponseDto("400", "이름 공백");
            return result;
        } catch (NullPwException e) {
            result = new SignupParentResponseDto("400", "비밀번호 공백");
            return result;
        } catch (NullCityException e) {
            result = new SignupParentResponseDto("400", "도시 공백");
            return result;
        }
    }

    // 지영아 여기다가 로그인 코드 작성해조!
}
