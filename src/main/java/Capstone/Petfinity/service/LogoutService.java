package Capstone.Petfinity.service;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.logout.LogoutRequestDto;
import Capstone.Petfinity.exception.logout.InvalidStatusException;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LogoutService {

    private final ParentRepository parentRepository;
    private final VetRepository vetRepository;

    @Transactional
    public void logout(LogoutRequestDto request) {

        String uuid = request.getUuid();

        if (request.getWho()) { // True: 보호자, False: 수의사

            Parent parent = parentRepository.findOneByUuid(uuid);
            validateParentStatus(parent); // 로그인 상태인지 확인

            parentRepository.changeLoginStatus(parent);
            log.info("로그아웃 성공");
        } else {
            Vet vet = vetRepository.findOneByUuid(uuid);
            validateVetStatus(vet); // 로그인 상태인지 확인

            vetRepository.changeLoginStatus(vet);
            log.info("로그아웃 성공");
        }

    }

    public void validateParentStatus(Parent parent) {
        if (!parent.getLogin_status()) {
            log.info("이미 로그아웃 상태입니다");
            throw new InvalidStatusException();
        }
    }

    public void validateVetStatus(Vet vet) {
        if (!vet.getLogin_status()) {
            log.info("이미 로그아웃 상태입니다");
            throw new InvalidStatusException();
        }
    }

}
