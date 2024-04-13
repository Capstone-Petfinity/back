package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.parent.SignupParentReqDto;
import Capstone.Petfinity.dto.parent.SignupParentResDto;
import Capstone.Petfinity.exception.signup.*;
import Capstone.Petfinity.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ParentApiController {

    @Autowired
    private final ParentService parentService;

    SignupParentResDto result;

    @PostMapping("/user/signup/parent")
    public SignupParentResDto signupParent(@RequestHeader("auth") String auth,
                                           @RequestBody SignupParentReqDto request) {

        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new SignupParentResDto("400", "권한이 없습니다.");
            return result;
        }
// result = new LoginResponseDto(statusCode:"200", uuid: parent.getUuid()
        try {
            log.info("Start Signup");
            parentService.signup(request);
            result = new SignupParentResDto("200", "Signup Success");
            return result;
        } catch (InvalidPhoneNumberException e) {
            result = new SignupParentResDto("401", "유효하지 않는 전화번호");
            return result;
        } catch (InvalidPwException e) {
            result = new SignupParentResDto("401", "유효하지 않는 비밀번호");
            return result;
        } catch (InvalidNameException e) {
            result = new SignupParentResDto("401", "유효하지 않는 이름");
            return result;
        }catch (DuplicatePhoneNumberException e) {
            result = new SignupParentResDto("402", "중복된 전화번호");
            return result;
        } catch (NullNameException e) {
            result = new SignupParentResDto("403", "이름 공백");
            return result;
        } catch (NullPwException e) {
            result = new SignupParentResDto("403", "비밀번호 공백");
            return result;
        } catch (NullCityException e) {
            result = new SignupParentResDto("403", "도시 공백");
            return result;
        }
    }
}
