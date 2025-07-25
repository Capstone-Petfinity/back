package Capstone.Petfinity.service.loginout;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.domain.Vet;
import Capstone.Petfinity.dto.loginout.LogoutReqDto;
import Capstone.Petfinity.exception.FailLogoutException;
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
    public void logout(LogoutReqDto request) {

        String uuid;
        Boolean isParent;

        uuid = request.getUuid();
        isParent = request.getIsParent();

        if (isParent) { // True: 보호자, False: 수의사

            Parent parent = parentRepository.findOneByUuid(uuid);
            if (parent == null)
                throw new FailLogoutException();

            validateParentStatus(parent); // 로그인 상태인지 확인

            parentRepository.changeLoginStatus(parent);
            log.info("로그아웃 성공");
        } else if (!isParent) {

            Vet vet = vetRepository.findOneByUuid(uuid);
            if (vet == null)
                throw new FailLogoutException();

            validateVetStatus(vet); // 로그인 상태인지 확인

            vetRepository.changeLoginStatus(vet);
            log.info("로그아웃 성공");
        } else if (isParent == null) { // else로만 해도 되지 않을까?

            log.warn("로그아웃 실패");
            throw new FailLogoutException();
        }
    }

    public void validateParentStatus(Parent parent) {

        if (parent.getLogin_status())
            return;
        throw new FailLogoutException();
    }

    public void validateVetStatus(Vet vet) {

        if (vet.getLogin_status())
            return;
        throw new FailLogoutException();
    }
}
