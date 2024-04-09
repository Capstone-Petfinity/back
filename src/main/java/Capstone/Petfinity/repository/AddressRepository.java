package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.City;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepository {

    private final EntityManager em;

    public List<City> findAllCity() {
        return em.createQuery("select c from City c", City.class)
                .getResultList();
    }
}
