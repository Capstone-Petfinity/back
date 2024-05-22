package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.diagnosis.*;
import Capstone.Petfinity.dto.info.hospital.InfoHospitalResDto;
import Capstone.Petfinity.dto.info.parent.InfoParentReqDto;
import Capstone.Petfinity.dto.info.pet.InfoPetsResDto;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.LoginStatusException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DiagnosisApiController {

    @Autowired
    private final DiagnosisService diagnosisService;

    NormalResDto result;

    DiagnosisListResDto resultDiagnosisList;

    InfoDiagnosisResDto resultDiagnosis;

    @PostMapping("/user/savediagnosis")
    public NormalResDto savediagnosis(@RequestHeader("auth") String auth,
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

    @PostMapping("/user/diagnosislist")
    public DiagnosisListResDto diagnosisList(@RequestHeader("auth") String auth,
                                             @RequestBody DiagnosisListReqDto request){
        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultDiagnosisList = new DiagnosisListResDto("400", "권한 없음", null);
            return resultDiagnosisList;
        }

        log.info("진단 리스트 조회");
        try{

            List<DiagnosisListDto> diagnoses = diagnosisService.diagnosisList(request);

            resultDiagnosisList = new DiagnosisListResDto("200", "진단 리스트 조회 성공", diagnoses);
            return resultDiagnosisList;
        }catch (NotExistException e) {

            resultDiagnosisList = new DiagnosisListResDto("404", "존재하지 않는 회원", null);
            return resultDiagnosisList;
        }catch (LoginStatusException e) {

            resultDiagnosisList = new DiagnosisListResDto("406", "로그아웃 상태", null);
            return resultDiagnosisList;
        }
    }
    @PostMapping("/user/infodiagnosis")
    public InfoDiagnosisResDto infoDiagnosis(@RequestHeader("auth") String auth,
                                             @RequestBody InfoDiagnosisReqDto request){
        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            resultDiagnosis = new InfoDiagnosisResDto("400", "권한 없음", null, null, null, null);
            return resultDiagnosis;
        }

        log.info("진단 리스트 조회");
        try{

            Diagnosis diagnosis = diagnosisService.infoDiagnosis(request);

            resultDiagnosis = new InfoDiagnosisResDto("200", "진단 조회 성공", diagnosis.getDisease_name(), diagnosis.getDate(), diagnosis.getPercent(), diagnosis.getContent());
            return resultDiagnosis;
        }catch (NullUuidException e) {

            resultDiagnosis = new InfoDiagnosisResDto("403", "입력되지 않은 uuid", null, null, null, null);
            return resultDiagnosis;
        } catch (InvalidUuidException e) {

            resultDiagnosis = new InfoDiagnosisResDto("401", "유효하지 않은 uuid", null, null, null, null);
            return resultDiagnosis;
        } catch (NotExistException e) {

            resultDiagnosis = new InfoDiagnosisResDto("404", "존재하지 않는 진단", null, null, null, null);
            return resultDiagnosis;
        }
    }
}


