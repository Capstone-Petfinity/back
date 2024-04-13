package Capstone.Petfinity.api.signup;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.signup.parent.IdCheckReqDto;
import Capstone.Petfinity.exception.DuplicateIdException;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class IdDuplicateApiController {

    @Autowired
    private final ParentService parentService;

    NormalResDto idCheckResult;

    @PostMapping("/user/signup/parent/id")
    public NormalResDto duplicateId(@RequestHeader("auth") String auth,
                                     @RequestBody IdCheckReqDto request) {

        log.debug("Auth Check");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.error("No Authorization");
            idCheckResult = new NormalResDto("400", "권한 없음");
            return idCheckResult;
        }

        log.debug("Start Id Duplicate Check");
        try {
            parentService.idCheck(request);

            log.debug("Id Duplicate Check Success");
            idCheckResult = new NormalResDto("200", "아이디 확인 성공");
            return idCheckResult;
        } catch (InvalidIdException e) {

            idCheckResult = new NormalResDto("401", "유효하지 않는 아이디");
            return idCheckResult;
        } catch (DuplicateIdException e) {

            idCheckResult = new NormalResDto("403", "중복된 아이디");
            return idCheckResult;
        }
    }
}