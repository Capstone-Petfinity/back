package Capstone.Petfinity.service;

import Capstone.Petfinity.dto.logout.LogoutRequestDto;
import Capstone.Petfinity.repository.ParentRepository;
import Capstone.Petfinity.repository.VetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            parentRepository.findBy(request);
        }
    }
}
