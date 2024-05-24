package Capstone.Petfinity.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class AiService {

    private byte[] getBase64String(String string) throws Exception {

        return Base64.getDecoder().decode(string);
    }

    public String sendDataToAiServerTest(String user_type, String disease_area, String type, String disease, MultipartFile img) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String aiServerUrl = "http://203.250.148.132:5000/hello";

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Body 설정
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("user_type", user_type);
        body.add("disease_area", disease_area);
        body.add("type", type);
        body.add("disease", disease);
        body.add("img", img);

        // Request Entity 생성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // AI 서버로 요청 전송
        ResponseEntity<String> response = restTemplate.postForEntity(aiServerUrl, requestEntity, String.class);

        return response.getBody();
    }

    public String sendDataToAiServer(String userUuid, String user_type, String disease_area, String type, String position, String detail_area, String disease, MultipartFile img) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String aiServerUrl = "http://203.250.148.132:5000/formdata_test";

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Body 설정
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("userUuid", userUuid);
        body.add("user_type", user_type);
        body.add("disease_area", disease_area);
        body.add("type", type);
        body.add("position", position);
        body.add("detail_area", detail_area);
        body.add("disease", disease);
        body.add("img", img);

        // Request Entity 생성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // AI 서버로 요청 전송
        ResponseEntity<String> response = restTemplate.postForEntity(aiServerUrl, requestEntity, String.class);

        return response.getBody();
    }
}
