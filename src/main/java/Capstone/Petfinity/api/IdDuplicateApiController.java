package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.parent.IdCheckReqDto;
import Capstone.Petfinity.dto.parent.IdCheckResDto;
import Capstone.Petfinity.exception.signup.DuplicateIdException;
import Capstone.Petfinity.exception.signup.InvalidIdException;
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

    IdCheckResDto idCheckResult;

    @PostMapping("/user/signup/parent/id")
    public IdCheckResDto duplicateId(@RequestHeader("auth") String auth,
                                     @RequestBody IdCheckReqDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            idCheckResult = new IdCheckResDto("400", "권한이 없습니다.");
            return idCheckResult;
        }

        log.info("Start Id Duplicate Check");
        try {
            parentService.idCheck(request);
            idCheckResult = new IdCheckResDto("200", "IdCheck Success");
            return idCheckResult;
        } catch (InvalidIdException e) {
            idCheckResult = new IdCheckResDto("401", "유효하지 않는 아이디");
            return idCheckResult;
        } catch (DuplicateIdException e) {
            idCheckResult = new IdCheckResDto("403", "중복된 아이디");
            return idCheckResult;
        }
    }
}