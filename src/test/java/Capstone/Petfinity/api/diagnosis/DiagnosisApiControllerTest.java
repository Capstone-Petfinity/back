package Capstone.Petfinity.api.diagnosis;

import Capstone.Petfinity.service.diagnosis.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class DiagnosisApiControllerTest {

    @Autowired
    AiService aiService;

}