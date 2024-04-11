package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.parent.SignupParentRequestDto;
import Capstone.Petfinity.dto.parent.SignupParentResponseDto;
import Capstone.Petfinity.exception.signup.DuplicateIdException;
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
    private final ParentRepository parentRepository;

    SignupParentResponseDto result;

    @PostMapping("/user/signup/parent/id")
    public SignupParentResponseDto duplicateId(@RequestHeader("auth") String auth,
                                               @RequestBody SignupParentRequestDto request) {
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
            result = new SignupParentResponseDto("400", "권한이 없습니다.");
            return result;
        }

        log.info("Start Id Duplicate Check");
        if (!parentRepository.findById(request.getId()).isEmpty()) {
            result = new SignupParentResponseDto("403", "중복된 아이디");
            return result;
        }
        result = new SignupParentResponseDto("200", "Signup Success");
        return result;
    }
}