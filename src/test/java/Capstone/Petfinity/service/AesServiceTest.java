package Capstone.Petfinity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AesServiceTest {

    @Autowired
    AesService aesService;

    @Test
    public void aes() throws Exception {
        String test = "helloworld!";
        String encrypted = aesService.encryptAES(test);
        String decrypted = aesService.decryptAES(encrypted);

        System.out.println("encrypted = " + encrypted);
        System.out.println("decrypted = " + decrypted);

        assertEquals(test, decrypted);
    }
}