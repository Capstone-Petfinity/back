package Capstone.Petfinity.api.info;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Pet;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.info.hospital.InfoHospitalReqDto;
import Capstone.Petfinity.dto.info.hospital.InfoHospitalResDto;
import Capstone.Petfinity.dto.info.parent.InfoParentReqDto;
import Capstone.Petfinity.dto.info.parent.InfoParentResDto;
import Capstone.Petfinity.dto.info.pet.InfoPetsResDto;
import Capstone.Petfinity.dto.info.vet.InfoVetReqDto;
import Capstone.Petfinity.dto.info.vet.InfoVetResDto;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NotLoginStatusException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.user.ParentService;
import Capstone.Petfinity.service.user.PetService;
import Capstone.Petfinity.service.user.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InfoApiController {

    @Autowired
    private final ParentService parentService;
    @Autowired
    private final VetService vetService;
    @Autowired
    private final PetService petService;

    InfoParentResDto resultParent;
    InfoPetsResDto resultPet;
    InfoVetResDto resultVet;
    InfoHospitalResDto resultHospital;


    // 보호자 정보 API
    @PostMapping("/user/info/parent")
    public InfoParentResDto infoParent(@RequestHeader("auth") String auth,
                                       @RequestBody InfoParentReqDto request) {
        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultParent = new InfoParentResDto("400", "권한 없음", null, null, null, null, null, null);
            return resultParent;
        }

        log.info("보호자 정보 조회");
        try {
            Parent parent = parentService.infoParent(request);

            resultParent = new InfoParentResDto("200", "회원 정보 조회 성공", parent.getUuid(), parent.getId(), parent.getName(), parent.getPhone_number(), parent.getCity(), parent.getReservations());
            return resultParent;
        } catch (NullUuidException e) {

            resultParent = new InfoParentResDto("403", "입력되지 않은 uuid", null, null, null, null, null, null);
            return resultParent;
        } catch (InvalidUuidException e) {

            resultParent = new InfoParentResDto("401", "유효하지 않은 uuid", null, null, null, null, null, null);
            return resultParent;
        } catch (NotExistException e) {

            resultParent = new InfoParentResDto("404", "존재하지 않는 회원", null, null, null, null, null, null);
            return resultParent;
        } catch (NotLoginStatusException e) {

            resultParent = new InfoParentResDto("406", "로그아웃 상태", null, null, null, null, null, null);
            return resultParent;
        }
    }

    // 반려동물 정보 API
    @PostMapping("/user/info/pet")
    public InfoPetsResDto infoPets(@RequestHeader("auth") String auth,
                                   @RequestBody InfoParentReqDto request) {

        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultPet = new InfoPetsResDto("400", "권한 없음", null);
            return resultPet;
        }

        log.info("반려동물 정보 조회");
        try {
            List<Pet> pets = petService.infoPet(request);

            resultPet = new InfoPetsResDto("200", "회원 정보 조회 성공", pets);
            return resultPet;
        } catch (NotLoginStatusException e) {

            resultPet = new InfoPetsResDto("406", "로그아웃 상태", null);
            return resultPet;
        } catch (NullUuidException e) {

            resultPet = new InfoPetsResDto("403", "입력되지 않은 uuid", null);
            return resultPet;
        } catch (InvalidUuidException e) {

            resultPet = new InfoPetsResDto("401", "유효하지 않은 uuid", null);
            return resultPet;
        } catch (NotExistException e) {

            resultPet = new InfoPetsResDto("404", "존재하지 않는 회원", null);
            return resultPet;
        }
    }

    // 수의사 정보 API
    @PostMapping("/user/info/vet")
    public InfoVetResDto infoVet(@RequestHeader("auth") String auth,
                                 @RequestBody InfoVetReqDto request) {
        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultVet = new InfoVetResDto("400", "권한 없음", null, null, null);
            return resultVet;
        }

        log.info("수의사 정보 조회");
        try {
            Vet vet = vetService.infoVet(request);

            resultVet = new InfoVetResDto("200", "회원 정보 조회 성공", vet.getUuid(), vet.getId(), vet.getName());
            return resultVet;
        } catch (NotLoginStatusException e) {

            resultVet = new InfoVetResDto("406", "로그아웃 상태", null, null, null);
            return resultVet;
        } catch (NullUuidException e) {

            resultVet = new InfoVetResDto("403", "입력되지 않은 uuid", null, null, null);
            return resultVet;
        } catch (InvalidUuidException e) {

            resultVet = new InfoVetResDto("401", "유효하지 않은 uuid", null, null, null);
            return resultVet;
        } catch (NotExistException e) {

            resultVet = new InfoVetResDto("404", "존재하지 않는 회원", null, null, null);
            return resultVet;
        }
    }

    // 병원 정보 API
    @PostMapping("/info/hospital")
    public InfoHospitalResDto infoHospital(@RequestHeader("auth") String auth,
                                           @RequestBody InfoHospitalReqDto request) {
        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultHospital = new InfoHospitalResDto("400", "권한 없음", null);
            return resultHospital;
        }

        log.info("병원 정보 조회");
        try {
            List<Hospital> hospitalList = parentService.infoHospital(request);

            resultHospital = new InfoHospitalResDto("200", "병원 정보 조회 성공", hospitalList);
            return resultHospital;
        } catch (NullUuidException e) {

            resultHospital = new InfoHospitalResDto("403", "입력되지 않은 uuid", null);
            return resultHospital;
        } catch (InvalidUuidException e) {

            resultHospital = new InfoHospitalResDto("401", "유효하지 않은 uuid", null);
            return resultHospital;
        } catch (NotExistException e) {

            resultHospital = new InfoHospitalResDto("404", "존재하지 않는 회원", null);
            return resultHospital;
        } catch (NotLoginStatusException e) {

            resultHospital = new InfoHospitalResDto("406", "로그아웃 상태", null);
            return resultHospital;
        }

    }
}
