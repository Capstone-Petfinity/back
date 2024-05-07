package Capstone.Petfinity.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BcryptService {
    // 일단 미뤄둬 ㅋ
    BCrypt bCrypt = new BCrypt();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean isPwMatch(String pw, String dbPw) {
        return bCrypt.checkpw(pw, dbPw);
    }
}
