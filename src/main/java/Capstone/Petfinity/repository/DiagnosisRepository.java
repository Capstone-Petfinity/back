package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.dto.diagnosis.SaveDiagnosisReqDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepository {

    private final EntityManager em;

    public void save(SaveDiagnosisReqDto saveDiagnosisReqDto) {

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setUuid(UUID.randomUUID().toString());
        System.out.println("diagnosis = " + diagnosis);
        diagnosis.setDisease_name(saveDiagnosisReqDto.getDisease_name());
        diagnosis.setUser(saveDiagnosisReqDto.getUserUuid());
        diagnosis.setDate(saveDiagnosisReqDto.getDate());
        diagnosis.setPercent(saveDiagnosisReqDto.getPercent());
        diagnosis.setContent(saveDiagnosisReqDto.getContent());

        em.persist(diagnosis);
    }

}
