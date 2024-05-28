package Capstone.Petfinity.api.reservation;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.dto.info.hospital.HospitalListReqDto;
import Capstone.Petfinity.dto.info.hospital.HospitalListResDto;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NotLoginStatusException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.user.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HospitalListApiController {

    @Autowired
    ParentService parentService;

    @Value("${auth.key}")
    private String authKey;

    HospitalListResDto resultHospitalList;

    // 병원 리스트 API
    @PostMapping("/info/hospital/list")
    public HospitalListResDto hospitalList(@RequestHeader("auth") String auth,
                                           @RequestBody HospitalListReqDto request) {
        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            resultHospitalList = new HospitalListResDto("400", "권한 없음", null);
            return resultHospitalList;
        }

        log.info("병원 리스트 조회");
        try {
            List<Hospital> hospitalList = parentService.hospitalList(request);

            resultHospitalList = new HospitalListResDto("200", "병원 정보 조회 성공", hospitalList);
            return resultHospitalList;
        } catch (NullUuidException e) {

            resultHospitalList = new HospitalListResDto("403", "입력되지 않은 uuid", null);
            return resultHospitalList;
        } catch (InvalidUuidException e) {

            resultHospitalList = new HospitalListResDto("401", "유효하지 않은 uuid", null);
            return resultHospitalList;
        } catch (NotExistException e) {

            resultHospitalList = new HospitalListResDto("404", "존재하지 않는 회원", null);
            return resultHospitalList;
        } catch (NotLoginStatusException e) {

            resultHospitalList = new HospitalListResDto("406", "로그아웃 상태", null);
            return resultHospitalList;
        }
    }

}

