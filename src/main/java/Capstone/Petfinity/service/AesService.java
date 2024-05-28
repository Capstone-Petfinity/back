package Capstone.Petfinity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//@Slf4j
//public class AesService {
//
//    AesBytesEncryptor
//}

public class AesService {

    public static String algorithms = "AES/CBC/PKCS5Padding";

    private final static String AESKey = "abcdefghabcdefghabcdefghabcdefgh";

    private final static String AESIv = "0123456789abcdef";

    public String encrypt_AES(String ID) {

        try {
            String result;

            Cipher cipher = Cipher.getInstance(algorithms);

            SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

            IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            byte[] encrypted = cipher.doFinal(ID.getBytes(StandardCharsets.UTF_8));
            result = Base64.getEncoder().encodeToString(encrypted);

            return result;
        } catch (Exception e){

            System.out.println("암호화 중 오류 발생");
            e.printStackTrace();
        }
        return "";
    }

    String decrypt_AES(String ID) {

        try {
            Cipher cipher = Cipher.getInstance(algorithms);

            SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

            IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            byte[] decodeBytes = Base64.getDecoder().decode(ID);
            byte[] decrypted = cipher.doFinal(decodeBytes);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e){

            System.out.println("복호화 중 오류 발생");
            e.printStackTrace();
        }

        return "";
    }
}