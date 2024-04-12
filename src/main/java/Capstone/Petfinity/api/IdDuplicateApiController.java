package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.parent.IdCheckRequestDto;
import Capstone.Petfinity.dto.parent.IdCheckResponseDto;
import Capstone.Petfinity.dto.parent.SignupParentRequestDto;
import Capstone.Petfinity.dto.parent.SignupParentResponseDto;
import Capstone.Petfinity.exception.signup.DuplicateIdException;
import Capstone.Petfinity.exception.signup.InvalidIdException;
import Capstone.Petfinity.repository.ParentRepository;
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

    IdCheckResponseDto idCheckResult;

    @PostMapping("/user/signup/parent/id")
    public IdCheckResponseDto duplicateId(@RequestHeader("auth") String auth,
                                               @RequestBody IdCheckRequestDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            idCheckResult = new IdCheckResponseDto("400", "권한이 없습니다.");
            return idCheckResult;
        }

        log.info("Start Id Duplicate Check");
        try {
            parentService.idCheck(request);
            idCheckResult = new IdCheckResponseDto("200", "IdCheck Success");
            return idCheckResult;
        } catch (InvalidIdException e) {
            idCheckResult = new IdCheckResponseDto("401", "유효하지 않는 아이디");
            return idCheckResult;
        } catch (DuplicateIdException e) {
            idCheckResult = new IdCheckResponseDto("403", "중복된 아이디");
            return idCheckResult;
        }
    }
}