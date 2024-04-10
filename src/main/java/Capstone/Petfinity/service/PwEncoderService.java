package Capstone.Petfinity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwEncoderService {

    public String Encode(String pw) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPw = passwordEncoder.encode(pw);

        return hashedPw;
    }
}
