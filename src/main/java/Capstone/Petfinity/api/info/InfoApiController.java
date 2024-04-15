package Capstone.Petfinity.api.info;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.dto.info.*;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NotLoginStatusException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.ParentService;
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
public class InfoApiController {

    @Autowired
    private final ParentService parentService;
    @Autowired
    private final VetService vetService;

    InfoParentResDto resultParent;
    InfoPetsResDto resultPet;
    InfoVetResDto resultVet;

    @PostMapping("/user/info/parent")
    public InfoParentResDto infoParent(@RequestHeader("auth") String auth,
                                       @RequestBody InfoParentReqDto request) {
        log.debug("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultParent = new InfoParentResDto("400", "권한 없음", null, null, null, null, null);
            return resultParent;
        }

        log.debug("보호자 정보 조회");
        try {
            Parent parent = parentService.infoParent(request);

            resultParent = new InfoParentResDto("200", "회원 정보 조회 성공", parent.getUuid(), parent.getId(), parent.getName(), parent.getPhone_number(), parent.getCity());
            return resultParent;
        } catch (NotLoginStatusException e) {

            resultParent = new InfoParentResDto("406", "로그아웃 상태", null, null, null, null, null);
            return resultParent;
        } catch (NullUuidException e) {

            resultParent = new InfoParentResDto("403", "입력되지 않은 uuid", null, null, null, null, null);
            return resultParent;
        } catch (InvalidUuidException e) {

            resultParent = new InfoParentResDto("401", "유효하지 않는 uuid", null, null, null, null, null);
            return resultParent;
        } catch (NotExistException e) {

            resultParent = new InfoParentResDto("404", "존재하지 않는 회원", null, null, null, null, null);
            return resultParent;
        }
    }

    @PostMapping("/user/info/parent/pets")
    public InfoPetsResDto infoPets(@RequestHeader("auth") String auth,
                                   @RequestBody InfoPetsReqDto request) {

        log.debug("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultPet = new InfoPetsResDto("400", "권한 없음", null);
            return resultPet;
        }

        log.debug("보호자 정보 조회");
//        try {
//
//        }
        return null;
    }


    @PostMapping("/user/info/vet")
    public InfoVetResDto infoVet(@RequestHeader("auth") String auth,
                                 @RequestBody InfoVetReqDto request) {
        log.debug("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultVet = new InfoVetResDto("400", "권한 없음", null, null, null);
            return resultVet;
        }

        log.debug("수의사 정보 조회");
//        try {
//
//        } catch ()
        return null;
    }
}
