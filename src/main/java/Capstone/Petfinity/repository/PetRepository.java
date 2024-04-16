package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Pet;
import Capstone.Petfinity.dto.info.pet.RegisterPetReqDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PetRepository {

    private final EntityManager em;

    private final ParentRepository parentRepository;

    public void save(RegisterPetReqDto petDto){

        Pet pet = new Pet();

        pet.setUuid(UUID.randomUUID().toString());
        pet.setName(petDto.getName());
        pet.setBirth(petDto.getBirth());
        pet.setGender(petDto.getGender());
        pet.setKind(petDto.getKind());
        Parent parent = findParent(petDto.getParentUuid());
        pet.setParent(parent);

        em.persist(pet);
    }

    private Parent findParent(String parentUuid) {

        return parentRepository.findOneByUuid(parentUuid);
    }
}
