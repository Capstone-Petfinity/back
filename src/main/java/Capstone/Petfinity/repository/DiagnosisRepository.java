package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.dto.diagnosis.DiagnosisDto;
import Capstone.Petfinity.dto.diagnosis.DiagnosisListDto;
import Capstone.Petfinity.service.diagnosis.DiagnosisService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepository {

    private final EntityManager em;

    public void save(DiagnosisDto response) {

        Diagnosis diagnosis = new Diagnosis();

        diagnosis.setUuid(UUID.randomUUID().toString());
        diagnosis.setUser(response.getUser_uuid());
        diagnosis.setDisease_name(response.getDisease_name());
        diagnosis.setDate(LocalDate.now());
        diagnosis.setPercent(response.getPercent());
        diagnosis.setContent(response.getContent());
        diagnosis.setInsert_id(response.getInsert_id());

        em.persist(diagnosis);
    }

    public List<DiagnosisListDto> findDiagnoses(String uuid) {

        return em.createQuery("select new Capstone.Petfinity.dto.diagnosis.DiagnosisListDto" +
                        "(r.uuid, r.disease_name, r.date, r.percent, r.insert_id)" +
                        "from Diagnosis r where r.user = :uuid", DiagnosisListDto.class)
                .setParameter("uuid", uuid)
                .getResultList();
    }

    public Diagnosis findDiagnosis(String uuid) {

        return em.find(Diagnosis.class, uuid);
    }
}
