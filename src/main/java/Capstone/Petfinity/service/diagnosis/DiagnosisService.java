package Capstone.Petfinity.service.diagnosis;

import Capstone.Petfinity.dto.diagnosis.SaveDiagnosisReqDto;
import Capstone.Petfinity.repository.DiagnosisRepository;
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

        diagnosisRepository.save(diagnosis);
        log.info("진단결과 저장 성공");
    }
}
