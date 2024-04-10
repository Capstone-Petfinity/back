package Capstone.Petfinity.service;

import Capstone.Petfinity.repository.ParentRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ParentServiceTest {

    @Autowired
    ParentService parentService;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    EntityManager em;

    /*
    @Test
    public void 회원가입() throws Exception {
        LoginParentDTO parent = new LoginParentDTO();
        parent.setId("asdf");
        parent.setName("홍히지");

        UUID saveUuid = parentService.register(parent);

        em.flush();
        assertEquals(parent, parentRepository.findOne(saveUuid));
    }

    @Test
    public void 중복_아이디_예외() throws Exception {
        Parent parent1 = new Parent();
        parent1.setId("asdf");

        Parent parent2 = new Parent();
        parent2.setId("asdf");

        parentService.register(parent1);
        try {
            parentService.register(parent2);
        } catch (IllegalStateException e) {
            System.out.println("중복 예외 발생");
            return;
        }

        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 아이디_형식_예외() throws Exception {
        Parent parent = new Parent();
        parent.setId("afejfwae9ff 8");

        try {
            parentService.register(parent);
        } catch (IllegalStateException e) {
            System.out.println("아이디 형식 예외 발생");
            return;
        }

        fail("예외가 발생해야 한다.");
    }
     */
}