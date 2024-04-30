package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.domain.Parent;
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
//        PwEncoderService pwEncoderService = new PwEncoderService();

        parent.setUuid(UUID.randomUUID().toString());
        parent.setId(parentDTO.getId());
        parent.setName(parentDTO.getName());
        parent.setPw(parentDTO.getPw());

//        String pw = parentDTO.getPw();
//        parent.setPw(pwEncoderService.encode(pw));
//        System.out.println("pwEncoderService.isPwMatch(pw, parentDTO.getPw()) = " + pwEncoderService.isPwMatch(pw, parentDTO.getPw()));

        parent.setPhone_number(parentDTO.getPhone_number());
        parent.setCity(parentDTO.getCity());
        parent.setLogin_status(Boolean.FALSE);

        em.persist(parent);
    }

    public Parent findOneById(String id) {

        //return em.find(Parent.class, id); // em.find는 PK로만 찾을 수 있음...ㅅㅂ;
        return  em.createQuery("select p from Parent p where p.id = :id", Parent.class)
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

    public List<Hospital> findByAddress(Parent parent) {

        String findCity = parent.getCity();


        return em.createQuery("select h from Hospital h where h.city = :findCity order by rand() limit 10", Hospital.class)
                .setParameter("findCity", findCity)
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

    public boolean checkLoginStatus(Parent parent){

        return parent.getLogin_status();
    }

//    반려동물 조회 -> getter 로 해결.......
//    public List<Pet> findPetByUuid(String uuid) {
//
//        Parent parent = findOneByUuid(uuid);
//
//        return em.createQuery("select p from Pet p where p.parent = :uuid", Pet.class)
//                .setParameter("uuid", parent)
//                .getResultList();
//    }
}
