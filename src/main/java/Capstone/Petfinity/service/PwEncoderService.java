package Capstone.Petfinity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwEncoderService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean isPwMatch(String pw, String dbPw) {
        return passwordEncoder.matches(pw, dbPw);
    }
}
