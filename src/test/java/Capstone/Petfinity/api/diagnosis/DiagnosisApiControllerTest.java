package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DiagnosisApiControllerTest {

    @Autowired
    AiService aiService;

}