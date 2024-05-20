package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.diagnosis.SaveDiagnosisReqDto;
import Capstone.Petfinity.exception.LoginStatusException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DiagnosisApiController {

    @Autowired
    private final DiagnosisService diagnosisService;

    NormalResDto result;

    @PostMapping("/user/diagnosis")
    public NormalResDto diagnosis(@RequestHeader("auth") String auth,
                                  @RequestParam("disease_name") String disease_name,
                                  @RequestParam("userUuid") String userUuid,
                                  @RequestParam("date") LocalDate date,
                                  @RequestParam("percent") Double percent,
                                  @RequestParam("content") String content) {

        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            result = new NormalResDto("400", "권한 없음");
            return result;
        }

        log.info("질병 정보 저장");
        try {

            SaveDiagnosisReqDto request = new SaveDiagnosisReqDto(disease_name, userUuid, date, percent, content);
            diagnosisService.saveDiagnosis(request);

            result = new NormalResDto("200", "질병 정보 저장 성공");
            return result;
        } catch (NotExistException e) {

            result = new NormalResDto("404", "존재하지 않는 회원");
            return result;
        } catch (LoginStatusException e) {

            result = new NormalResDto("406", "로그아웃 상태");
            return result;
        }
    }
}
