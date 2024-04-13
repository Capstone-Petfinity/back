package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.signup.vet.SignupVetReqDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class VetRepository {

    private final EntityManager em;

    public void save(SignupVetReqDto vetDTO) {
        Vet vet = new Vet();

        vet.setUuid(UUID.randomUUID().toString());
        vet.setId(vetDTO.getId());
        vet.setName(vetDTO.getName());
        vet.setPw(vetDTO.getPw());
        vet.setLogin_status(Boolean.FALSE);

        em.persist(vet);
    }

    public Vet findOneByUuid(String uuid) {

        return em.find(Vet.class, uuid);
    }

    public Vet findOneById(String id) {

        return em.createQuery("select v from Vet v where v.id = :id", Vet.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Vet> findById(String id) {

        return em.createQuery("select v from Vet v where v.id = :id", Vet.class)
                .setParameter("id", id)
                .getResultList();
    }

    public void changeLoginStatus(Vet vet) {

        if (!vet.getLogin_status()) {
            vet.setLogin_status(Boolean.TRUE);
        } else {
            vet.setLogin_status(Boolean.FALSE);
        }

        em.persist(vet);
    }
}
