package Capstone.Petfinity.api;

import Capstone.Petfinity.service.AiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AiApi {

    @Autowired
    private AiService aiService;

    private String getBase64String(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }

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

//    @PostMapping("/diagnosis")
//    public String formData(@RequestParam("user_uuid") String user_uuid,
//                           @RequestParam("user_type") String user_type,
//                           @RequestParam("disease_area") String disease_area,
//                           @RequestParam("type") String type,
//                           @RequestParam("disease") String disease,
//                           @RequestParam("img") MultipartFile img) throws Exception {
//
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        String response = restTemplate.postForEntity(url);
//        response = aiService.sendDataToAiServerTest(user_type, disease_area, type, disease, img);
//        System.out.println("response = " + response);
//
//        System.out.println("Message from Flask server: " + response);
//        log.info("FormData 테스트");
//
//        return response;
//    }

//    @PostMapping("/formdata_test")
//    public String formData(@RequestParam("user_type") String user_type,
//                           @RequestParam("disease_area") String disease_area,
//                           @RequestParam("type") String type,
//                           @RequestParam("detail_area") String detail_area,
//                           @RequestParam("disease") String disease,
//                           @RequestParam("img") MultipartFile img) throws Exception {
//
//        String url = "http://203.250.148.132:5000/formdata_test";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        // ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        String response = aiService.sendDataToAiServer(user_type, disease_area, type, detail_area, disease, img);
//
//        System.out.println("Message from Flask server: " + response);
//        log.info("FormData 확인");
//
//        return response;
//    }


}