package Capstone.Petfinity.service;

import Capstone.Petfinity.DTO.LoginParentDTO;
import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    // 회원 가입
    @Transactional
    public void register(LoginParentDTO parent) {
        validateDuplicateParent(parent); // 아이디 중복 확인
        validateParent(parent); // 아이디 형식 확인
        parentRepository.save(parent);
    }

    private void validateParent(LoginParentDTO parent) {
        if (parent.getId().length() < 8 || !parent.getId().matches("^[a-zA-Z0-9]+$"))
            throw new IllegalStateException("아이디의 형식이 올바르지 않습니다.");
    }

    private void validateDuplicateParent(LoginParentDTO parent) {
        List<Parent> findParentsId = parentRepository.findById(parent.getId());
        if (!findParentsId.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        List<Parent> findParentsPhoneNumber = parentRepository.findByPhoneNumber(parent.getId());
        if (!findParentsPhoneNumber.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 번호입니다.");
        }
    }

//    @Transactional
//    public UUID login(Parent parent) {
//
//    }

}
