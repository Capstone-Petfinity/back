package Capstone.Petfinity.service.diagnosis;

import Capstone.Petfinity.dto.info.diagnosis.SaveDiagnosisReqDto;
import Capstone.Petfinity.dto.signup.parent.SignupParentReqDto;
import Capstone.Petfinity.repository.DiagnosisRepository;
import Capstone.Petfinity.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    @Transactional
    public void saveDiagnosis(SaveDiagnosisReqDto diagnosis) {


    }
}
