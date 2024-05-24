package Capstone.Petfinity;

import Capstone.Petfinity.api.AiApi;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class AiApiTest {
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    AiApi aiApi;

    @Test
    public void testRequestToFlask() throws Exception {
//		// Given
//		String fileName = "test.jpg";
//		MultipartFile file = Mockito.mock(MultipartFile.class);
//		Mockito.when(file.getBytes()).thenReturn(new byte[0]); // 파일 데이터 설정
//
//		ResponseEntity<String> mockResponse = new ResponseEntity<>("{\"message\": \"success\"}", HttpStatus.OK);
//		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(mockResponse);

        // When
        String message = aiApi.requestToFlask();

        // Then
        assertEquals(message, "Hello, World!");
    }

//    @Test
//    public void testFormData() throws Exception {
//		// Given
//
//        String message = aiApi.formData();
//
//        // Then
//    }
}
