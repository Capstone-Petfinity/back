package Capstone.Petfinity.service;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.info.RegisterPetReqDto;
import Capstone.Petfinity.exception.NullNameException;
import Capstone.Petfinity.exception.NullPetGenderException;
import Capstone.Petfinity.exception.NullPetKindException;
import Capstone.Petfinity.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;

    @Transactional
    public void registerPet(RegisterPetReqDto pet){

        nullPet(pet);// null 확인
        petRepository.save(pet);
        log.debug("반려동물 등록 성공");
    }

    private void nullPet(RegisterPetReqDto pet){

        if(pet.getName().isEmpty()) {

            throw new NullNameException();
        }
        if(pet.getGender().isEmpty()) {

            throw new NullPetGenderException();
        }
        if(pet.getKind().isEmpty()) {

            throw new NullPetKindException();
        }
    }
}
