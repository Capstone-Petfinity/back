package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.diagnosis.SaveDiagnosisReqDto;
import Capstone.Petfinity.service.diagnosis.DiagnosisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SaveDiagnosisApiController {

    @Autowired
    private final DiagnosisService diagnosisService;

    NormalResDto result;

    @PostMapping("/info/diagnosis")
    public NormalResDto saveDiagnosis(@RequestHeader("auth") String auth,
                                      @RequestParam("uuid") String uuid, @RequestParam("disease_name") String disease_name,
                                      @RequestParam("parent_uuid") String parent_uuid, @RequestParam("date") LocalDate date,
                                      @RequestParam("percent") Double percent, @RequestParam("content") String content,
                                      @RequestParam("image") byte[] image) {

        log.info("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            result = new NormalResDto("400", "권한 없음");
            return result;
        }

        log.info("질병 정보 저장");
        try {
            SaveDiagnosisReqDto saveDiagnosisReqDto =
                    new SaveDiagnosisReqDto(uuid, disease_name, parent_uuid, date, percent, content, image);

            diagnosisService.saveDiagnosis(saveDiagnosisReqDto);
            result = new NormalResDto("200", "질병 정보 저장 성공");
            return result;
        } catch (IllegalStateException e) {

            result = new NormalResDto("401", "ㅇ렁나ㅣ러");
            return result;
        }
    }
}
