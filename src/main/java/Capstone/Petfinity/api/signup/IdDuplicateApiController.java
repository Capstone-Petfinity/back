package Capstone.Petfinity.api.signup;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.signup.parent.IdCheckReqDto;
import Capstone.Petfinity.exception.DuplicateIdException;
import Capstone.Petfinity.exception.InvalidIdException;
import Capstone.Petfinity.service.ParentService;
import Capstone.Petfinity.service.VetService;
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
    @Autowired
    private final VetService vetService;

    NormalResDto idCheckResult;

    @PostMapping("/user/signup/parent/idduplicate")
    public NormalResDto parentDuplicateId(@RequestHeader("auth") String auth,
                                     @RequestBody IdCheckReqDto request) {

        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            idCheckResult = new NormalResDto("400", "권한 없음");
            return idCheckResult;
        }

        log.info("아이디 중복 확인 시작");
        try {
            parentService.idCheck(request);

            log.info("아이디 중복 확인 성공");
            idCheckResult = new NormalResDto("200", "아이디 확인 성공");
            return idCheckResult;
        } catch (InvalidIdException e) {

            idCheckResult = new NormalResDto("401", "유효하지 않은 아이디");
            return idCheckResult;
        } catch (DuplicateIdException e) {

            idCheckResult = new NormalResDto("402", "중복된 아이디");
            return idCheckResult;
        }
    }

    @PostMapping("/user/signup/vet/idduplicate")
    public NormalResDto vetDuplicateId(@RequestHeader("auth") String auth,
                                    @RequestBody IdCheckReqDto request) {

        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            idCheckResult = new NormalResDto("400", "권한 없음");
            return idCheckResult;
        }

        log.info("아이디 중복 확인 시작");
        try {
            vetService.idCheck(request);

            log.info("아이디 중복 확인 성공");
            idCheckResult = new NormalResDto("200", "아이디 확인 성공");
            return idCheckResult;
        } catch (InvalidIdException e) {

            idCheckResult = new NormalResDto("401", "유효하지 않은 아이디");
            return idCheckResult;
        } catch (DuplicateIdException e) {

            idCheckResult = new NormalResDto("402", "중복된 아이디");
            return idCheckResult;
        }
    }
}