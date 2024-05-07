package Capstone.Petfinity.service.user;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.service.BcryptService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ParentServiceTest {

    @Autowired
    BcryptService bcryptService;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Parent parent = new Parent();
        parent.setPw("qwerasdf1234");

        //when
        String encodedPw = bcryptService.encode(parent.getPw());

        SignupParentReqDto signupParentReqDto = new SignupParentReqDto("asdfghjk", parent.getPw(), "김옥지", "01023459987", "송파구");
        parentRepository.save(signupParentReqDto);

        //then
        System.out.println("encodedPw = " + encodedPw);
        //assertEquals(member, memberRepository.findOne(savedId));
    }
}