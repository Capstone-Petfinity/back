package Capstone.Petfinity.repository;

import Capstone.Petfinity.domain.Diagnosis;
import Capstone.Petfinity.dto.diagnosis.DiagnosisListDto;
import Capstone.Petfinity.dto.diagnosis.SaveDiagnosisReqDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
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
        diagnosis.setImage(saveDiagnosisReqDto.getImage());

        em.persist(diagnosis);
    }

    public List<DiagnosisListDto> findDiagnoses(String uuid) {

        return em.createQuery("select new Capstone.Petfinity.dto.diagnosis.DiagnosisListDto" +
                        "(r.disease_name, r.date) " +
                        "from Diagnosis r where r.user = :uuid", DiagnosisListDto.class)
                .setParameter("uuid", uuid)
                .getResultList();
    }

    public Diagnosis findDiagnosis(String diagnosisUuid) {

        return em.find(Diagnosis.class, diagnosisUuid);
    }

    private String getBase64String(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}
