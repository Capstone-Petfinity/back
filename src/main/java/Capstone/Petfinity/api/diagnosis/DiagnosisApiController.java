package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.diagnosis.*;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.LoginStatusException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.diagnosis.AiService;
import Capstone.Petfinity.service.diagnosis.DiagnosisService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DiagnosisApiController {

    @Autowired
    private final DiagnosisService diagnosisService;
    @Autowired
    private final AiService aiService;

    @Value("${auth.key}")
    private String authKey;

    DiagnosisListResDto resultDiagnosisList;
    InfoDiagnosisResDto resultDiagnosis;

    @PostMapping("user/diagnosis")
    public AiResDto diagnosis(@RequestHeader("auth") String auth,
                              @RequestBody AiReqDto request) {

        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            return new AiResDto("400", "권한 없음", null, null, null, null, null);
        }

        log.info("AI서버에 데이터 전송");
        try {
            System.out.println("request = " + request);
            // AI 서버로 데이터 전송
            DiagnosisDto diagnosis = aiService.sendDataToAi(request);
            System.out.println("diagnosis = " + diagnosis);
            // DB에 진단결과 저장
            log.info("DB에 진단결과 저장");
            diagnosisService.saveDiagnosis(diagnosis);

            return new AiResDto("200", "ai 진단 성공", diagnosis.getUser_uuid(), diagnosis.getDisease_name(), diagnosis.getPercent(), diagnosis.getContent(), diagnosis.getInsert_id());
        } catch (Exception e) {

            return new AiResDto("401", "에러 발생", null, null, null, null, null);
        }
    }

    @PostMapping("/user/diagnosislist")
    public DiagnosisListResDto diagnosisList(@RequestHeader("auth") String auth,
                                             @RequestBody DiagnosisListReqDto request){
        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            resultDiagnosisList = new DiagnosisListResDto("400", "권한 없음", null);
            return resultDiagnosisList;
        }

        log.info("진단결과 리스트 조회");
        try {
            List<DiagnosisListDto> diagnoses = diagnosisService.diagnosisList(request);

            resultDiagnosisList = new DiagnosisListResDto("200", "진단결과 리스트 조회 성공", diagnoses);
            return resultDiagnosisList;
        } catch (NotExistException e) {

            resultDiagnosisList = new DiagnosisListResDto("404", "존재하지 않는 회원", null);
            return resultDiagnosisList;
        } catch (LoginStatusException e) {

            resultDiagnosisList = new DiagnosisListResDto("406", "로그아웃 상태", null);
            return resultDiagnosisList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/infodiagnosis")
    public InfoDiagnosisResDto infoDiagnosis(@RequestHeader("auth") String auth,
                                             @RequestBody InfoDiagnosisReqDto request){
        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            resultDiagnosis = new InfoDiagnosisResDto("400", "권한 없음", null, null, null, null, null);
            return resultDiagnosis;
        }

        log.info("진단결과 정보 조회");
        try {
            Diagnosis diagnosis = diagnosisService.infoDiagnosis(request);

            resultDiagnosis = new InfoDiagnosisResDto("200", "진단결과 정보 조회 성공", diagnosis.getDisease_name(), diagnosis.getDate(), diagnosis.getPercent(), diagnosis.getContent(), diagnosis.getInsert_id());
            return resultDiagnosis;
        } catch (NullUuidException e) {

            resultDiagnosis = new InfoDiagnosisResDto("403", "입력되지 않은 uuid", null, null, null, null, null);
            return resultDiagnosis;
        } catch (InvalidUuidException e) {

            resultDiagnosis = new InfoDiagnosisResDto("401", "유효하지 않은 uuid", null, null, null, null, null);
            return resultDiagnosis;
        } catch (NotExistException e) {

            resultDiagnosis = new InfoDiagnosisResDto("404", "존재하지 않는 진단", null, null, null, null, null);
            return resultDiagnosis;
        }
    }

    // Flask API Test
    public String requestToFlask() throws Exception {

        String aiServerUrl = "http://203.250.148.132:5000/hello";

        RestTemplate restTemplate = new RestTemplate();

        // Request
        ResponseEntity<String> response = restTemplate.getForEntity(aiServerUrl, String.class);

        // JSON 문자열을 파싱하여 JsonNode 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        // "message" 필드의 값 추출
        String message = jsonNode.get("message").asText();

        System.out.println("Message from Flask server: " + message);
        log.info("Hello 확인");

        return message;
    }
}


