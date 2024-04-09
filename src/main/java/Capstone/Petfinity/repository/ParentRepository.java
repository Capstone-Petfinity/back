package Capstone.Petfinity.repository;

import Capstone.Petfinity.DTO.LoginParentDTO;
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

    public void save(LoginParentDTO parentDTO) {
        Parent parent = new Parent();

        parent.setId(parent.getId());
        parent.setName(parentDTO.getName());
        parent.setPw(parentDTO.getPw());
        parent.setPhone_number(parentDTO.getPhone_number());
        parent.setAddress(parentDTO.getAddress());

        em.persist(parent);
    }

    public Parent findOne(UUID uuid) {
        return em.find(Parent.class, uuid);
    }

    public List<Parent> findById(String id) {
        return em.createQuery("select p from Parent p where p.id = :id", Parent.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void success_login_status(Parent parent) {
        parent.setLogin_status(true);

    }

    public List<Parent> findByPhoneNumber(String phoneNumber) {
        return em.createQuery("select p from Parent p where p.phone_number = :phoneNumber", Parent.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
    }
}
