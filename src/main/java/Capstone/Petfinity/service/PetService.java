package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Pet;
import Capstone.Petfinity.dto.info.parent.InfoParentReqDto;
import Capstone.Petfinity.dto.info.pet.RegisterPetReqDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.StringUtils.containsWhitespace;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetRepository petRepository;
    private final ParentRepository parentRepository;

    @Transactional
    public void registerPet(RegisterPetReqDto pet){

        nullPet(pet);// null 확인
        checkParentUuid(pet);// parentUuid null, validate 확인
        checkParentLoginStatus(pet);// parent loginStatus 확인
        petRepository.save(pet);
        log.info("반려동물 등록 성공");
    }

    public List<Pet> infoPet(InfoParentReqDto parent) {

        Parent findParent;

        if (parent.getUuid().isEmpty()) {
            log.warn("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(parent.getUuid()) || parent.getUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (parentRepository.findOneByUuid(parent.getUuid()) == null) {
            log.warn("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }

        findParent = parentRepository.findOneByUuid(parent.getUuid());
        loginStatusParent(findParent);
        log.info("로그인 상태 확인 성공");

        return findParent.getPets();
    }

    private void nullPet(RegisterPetReqDto pet) {

        if (pet.getName().isEmpty()) {

            throw new NullNameException();
        }
        if (pet.getGender().isEmpty()) {

            throw new NullPetGenderException();
        }
        if (pet.getKind().isEmpty()) {

            throw new NullPetKindException();
        }
    }

    private void checkParentUuid(RegisterPetReqDto pet) {

        if (pet.getParentUuid().isEmpty()) {
            log.warn("uuid가 비어있습니다");
            throw new NullUuidException();
        }
        if (containsWhitespace(pet.getParentUuid()) || pet.getParentUuid().length() != 36) {
            log.warn("유효하지 않은 uuid입니다");
            throw new InvalidUuidException();
        }
        if (parentRepository.findOneByUuid(pet.getParentUuid()) == null) {
            log.warn("보호자가 존재하지 않습니다");
            throw new NotExistException();
        }

    }

    private void checkParentLoginStatus(RegisterPetReqDto pet) {

        Parent findParent = parentRepository.findOneByUuid(pet.getParentUuid());
        if (!parentRepository.checkLoginStatus(findParent)){
            throw new NotLoginStatusException();
        }
    }

    private void loginStatusParent(Parent parent) {

        if (!parent.getLogin_status()) {
            log.warn("로그인 상태가 아닙니다");
            throw new NotLoginStatusException();
        }
    }
}
