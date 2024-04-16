package Capstone.Petfinity.api.info;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.info.RegisterPetReqDto;
import Capstone.Petfinity.dto.loginout.LoginResDto;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.service.PetService;
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
public class RegisterPetApiController {

    @Autowired
    private final PetService petService;

    NormalResDto result;

    @PostMapping("/user/parent/registerpet")
    public NormalResDto registerPet(@RequestHeader("auth") String auth,
                                    @RequestBody RegisterPetReqDto request) {

        log.debug("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            result = new NormalResDto("400", "권한 없음");
            return result;
        }

        log.debug("동물 등록 시작");
        try {
            petService.registerPet(request);

            log.debug("반려동물 등록 성공");
            result = new NormalResDto("200", "반려동물 등록 성공");
            return result;
        } catch (NullNameException e) {

            result = new NormalResDto("403", "입력되지 않은 이름");
            return result;
        } catch (NullPetGenderException e) {

            result = new NormalResDto("403", "입력되지 않은 성별");
            return result;
        } catch (NullPetKindException e) {

            result = new NormalResDto("403", "입력되지 않은 품종");
            return result;
        } catch (NullUuidException e) {

            result = new NormalResDto("403", "입력되지 않은 uuid");
            return result;
        } catch (InvalidUuidException e) {

            result = new NormalResDto("401", "유효하지 않은 uuid");
            return result;
        } catch (NotExistException e) {

            result = new NormalResDto("404", "존재하지 않는 회원");
            return result;
        } catch (NotLoginStatusException e) {

            result = new NormalResDto("406", "로그아웃된 상태");
            return result;
        }
    }
}
