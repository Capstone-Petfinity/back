package Capstone.Petfinity.service.diagnosis;

import Capstone.Petfinity.dto.diagnosis.AiReqDto;
import Capstone.Petfinity.dto.diagnosis.DiagnosisDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AiService {

    // 데이터를 JSON 객체로 변환하기 위해서 사용
    private final ObjectMapper objectMapper;

    public DiagnosisDto sendDataToAi(AiReqDto request) throws Exception {

        log.info("Ai 데이터 전송 시작");
        // 외부 API를 사용하기 위해
        RestTemplate restTemplate = new RestTemplate();

        String aiServerUrl = "http://203.250.148.132:5000/diagnosis";

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        // 파라미터로 들어온 dto를 JSON 객체로 변환
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Body 설정
        String body = objectMapper.writeValueAsString(request);

        // Request Message 설정
        HttpEntity<?> requestMessage = new HttpEntity<>(body, headers);

        log.info("AI서버로 요청 전송");
        // AI서버로 요청 전송
        HttpEntity<String> response = restTemplate.postForEntity(aiServerUrl, requestMessage, String.class);

        log.info("JSON에서 값 추출");
        // JSON에서 값 추출
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        String user_uuid = jsonNode.get("user_uuid").asText();
        String disease_name = jsonNode.get("disease_name").asText();
        String percent = jsonNode.get("percent").asText();
        String content = jsonNode.get("content").asText();
        String insert_id = jsonNode.get("insert_id").asText();

        log.info("AI 데이터 전송 완료");
        return new DiagnosisDto(user_uuid, disease_name, percent, content, insert_id);
    }
}
