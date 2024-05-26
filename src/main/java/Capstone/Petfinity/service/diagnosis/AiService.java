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

    public String sendDataToAi(AiReqDto request) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String aiServerUrl = "http://203.250.148.132:5000/send_ai";

        // Header 설정
        HttpHeaders headers = new HttpHeaders();

        // 파라미터로 들어온 dto를 JSON 객체로 변환
        headers.setContentType(MediaType.APPLICATION_JSON);

        String param = objectMapper.writeValueAsString(request);

        // Request Entity 생성
        HttpEntity<String> requestEntity = new HttpEntity<String>(param, headers);

        // AI 서버로 요청 전송
        return restTemplate.postForObject(aiServerUrl, requestEntity, String.class);
    }

    public DiagnosisDto sendDataToFront() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        String aiServerUrl = "http://203.250.148.132:5000/send_front";

        ResponseEntity<String> result = restTemplate.getForEntity(aiServerUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result.getBody());

        String user_uuid = jsonNode.get("user_uuid").asText();
        String disease_name = jsonNode.get("disease_name").asText();
        Double percent = jsonNode.get("percent").asDouble();
        String content = jsonNode.get("content").asText();
        String insert_id = jsonNode.get("insert_id").asText();

        return new DiagnosisDto(user_uuid, disease_name, percent, content, insert_id);
    }
}
