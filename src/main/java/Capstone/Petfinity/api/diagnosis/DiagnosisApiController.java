package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.diagnosis.*;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.LoginStatusException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.AiService;
import Capstone.Petfinity.service.diagnosis.DiagnosisService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DiagnosisApiController {

    @Autowired
    private final DiagnosisService diagnosisService;
    @Autowired
    private final AiService aiService;

    NormalResDto result;
    DiagnosisListResDto resultDiagnosisList;
    InfoDiagnosisResDto resultDiagnosis;

    public String requestToFlask() throws Exception {

        String aiServerUrl = "http://203.250.148.132:5000/hello";

        RestTemplate restTemplate = new RestTemplate();

//        // Header set
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        // Body set
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        String imageFileString = getBase64String(file);
//        body.add("filename", fileName);
//        body.add("image", imageFileString);
//
//        // Message
//        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);

        // Request
        ResponseEntity<String> response = restTemplate.getForEntity(aiServerUrl, String.class);

        // JSON 문자열을 파싱하여 JsonNode 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        // "message" 필드의 값 추출
        String message = jsonNode.get("message").asText();

        System.out.println("Message from Flask server: " + message);
        log.info("Hello 확인");

//        // Response 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        FlaskResponseDto dto = objectMapper.readValue(response.getBody(), FlaskResponseDto.class);

        return message;
    }

    // 프론트에서 formdata 형식으로 데이터 받아서 ai 서버에 전송(RequestParam으로 받아야 함)
    // 이미지: MultipartFile로 받아서 그대로 ai 서버에 전송
    @PostMapping("user/receive/diagnosis")
    public ResponseEntity<String> sendToAi(@RequestParam("user_uuid") String user_uuid,
                             @RequestParam("user_type") String user_type,
                             @RequestParam("disease_area") String disease_area,
                             @RequestParam("type") String type,
                             @RequestParam("position") String position,
                             @RequestParam("detail_type") String detail_area,
                             @RequestParam("disease") String disease,
                             @RequestParam("img") MultipartFile img) {

        log.info("ai서버에 데이터 전송");
        try {
            // AI 서버로 데이터 전송
            String result = aiService.sendDataToAiServer(user_uuid, user_type, disease_area, type, position, detail_area, disease, img);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ai 서버에서 json 형식으로 데이터 받아서 프론트에 전송
    @PostMapping("user/send/diagnosis")
    public AiResDto sendToFront(@RequestParam("userUuid") String userUuid,
                                @RequestParam("disease_name") String disease_name,
                                @RequestParam("percent") Double percent,
                                @RequestParam("content") String content,
                                @RequestParam("img") byte[] img) {

        log.info("DB에 저장 후 프론트에 데이터 전송");
        try {

            SaveDiagnosisReqDto request = new SaveDiagnosisReqDto(userUuid, disease_name, LocalDate.now(), percent, content, img);
            diagnosisService.saveDiagnosis(request);

            // 프론트로 데이터 전송
            return new AiResDto("200", "ai 진단 성공", userUuid, disease_name, percent, content);
        } catch (Exception e) {

            return new AiResDto("400", "에러", null, null, null, null);
        }
    }

//    @PostMapping("/user/savediagnosis")
//    public NormalResDto saveDiagnosis(@RequestHeader("auth") String auth,
//                                      @RequestParam("userUuid") String userUuid,
//                                      @RequestParam("disease_name") String disease_name,
//                                      @RequestParam("date") LocalDate date,
//                                      @RequestParam("percent") Double percent,
//                                      @RequestParam("content") String content) {
//
//        log.info("권한 확인");
//        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
//
//            log.warn("권한이 없습니다");
//            result = new NormalResDto("400", "권한 없음");
//            return result;
//        }
//
//        log.info("질병 정보 저장");
//        try {
//
//            SaveDiagnosisReqDto request = new SaveDiagnosisReqDto(disease_name, userUuid, date, percent, content);
//            diagnosisService.saveDiagnosis(request);
//
//            result = new NormalResDto("200", "질병 정보 저장 성공");
//            return result;
//        } catch (NotExistException e) {
//
//            result = new NormalResDto("404", "존재하지 않는 회원");
//            return result;
//        } catch (LoginStatusException e) {
//
//            result = new NormalResDto("406", "로그아웃 상태");
//            return result;
//        }
//    }

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
        } catch (NotExistException e) {

            resultDiagnosisList = new DiagnosisListResDto("404", "존재하지 않는 회원", null);
            return resultDiagnosisList;
        } catch (LoginStatusException e) {

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
        try {

            Diagnosis diagnosis = diagnosisService.infoDiagnosis(request);

            resultDiagnosis = new InfoDiagnosisResDto("200", "진단 조회 성공", diagnosis.getDisease_name(), diagnosis.getDate(), diagnosis.getPercent(), diagnosis.getContent());
            return resultDiagnosis;
        } catch (NullUuidException e) {

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


