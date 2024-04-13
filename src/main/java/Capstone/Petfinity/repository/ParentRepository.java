package Capstone.Petfinity.repository;

import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.service.PwEncoderService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ParentRepository {

    private final EntityManager em;

    public void save(SignupParentReqDto parentDTO) {

        Parent parent = new Parent();
        PwEncoderService pwEncoderService = new PwEncoderService();

        parent.setUuid(UUID.randomUUID().toString());
        parent.setId(parentDTO.getId());
        parent.setName(parentDTO.getName());

        String pw = parentDTO.getPw();
        parent.setPw(pwEncoderService.encode(pw));
        System.out.println("pwEncoderService.isPwMatch(pw, parentDTO.getPw()) = " + pwEncoderService.isPwMatch(pw, parentDTO.getPw()));

        parent.setPhone_number(parentDTO.getPhone_number());
        parent.setCity(parentDTO.getCity());
        parent.setLogin_status(Boolean.FALSE);

        em.persist(parent);
    }

    public Parent findOneById(String id) {

        //return em.find(Parent.class, id); // em.find는 PK로만 찾을 수 있음...ㅅㅂ;
        return em.createQuery("select p from Parent p where p.id = :id", Parent.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Parent findOneByUuid(String uuid) {

        return em.find(Parent.class, uuid);
    }

    public List<Parent> findById(String id) {

        return em.createQuery("select p from Parent p where p.id = :id", Parent.class)
                .setParameter("id", id)
                .getResultList();
    }
    public List<Parent> findByPhoneNumber(String phoneNumber) {

        return em.createQuery("select p from Parent p where p.phone_number = :phoneNumber", Parent.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }
    public void changeLoginStatus(Parent parent) {

        if (!parent.getLogin_status()) {
            parent.setLogin_status(Boolean.TRUE);
        } else {
            parent.setLogin_status(Boolean.FALSE);
        }

        em.persist(parent);
    }
}
