package Capstone.Petfinity.api;

import Capstone.Petfinity.service.diagnosis.AiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AiApi {

    @Autowired
    private AiService aiService;

    public String requestToFlask() throws Exception {

        String url = "http://203.250.148.132:5000/hello";

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
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

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

    @PostMapping("/diagnosis/receive/test")
    public MultiValueMap<String, Object> formData(@RequestParam("img") MultipartFile img) throws Exception {


        // RestTemplate restTemplate = new RestTemplate();

//        String encodedImage = Base64.getEncoder().encodeToString(img.getBytes());
//        System.out.println("encodedImage = " + encodedImage);
//        FormData formData = new FormData(encodedImage);

        log.info("FormData 테스트");
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", img);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        return body;
    }

//    @PostMapping("/formdata_test")
//    public String formData(@RequestBody FormData formData) throws Exception {
//
//        String url = "http://203.250.148.132:5000/formdata_test";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<String> request = restTemplate.getForEntity(url, String.class);
//
//
//
//
//        // ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        res = aiService.sendDataToAiServerTest(user_type, disease_area, type, disease, image);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(response);
//
//
//        // "message" 필드의 값 추출
//        String message = jsonNode.get("image").asText();
//        String info = jsonNode.get("info").asText();
//
//        System.out.println("Message from Flask server: " + response);
//        System.out.println("message = " + message);
//        System.out.println("info = " + info);
//
//        log.info("FormData 확인");
//
//        return response;
//    }
}