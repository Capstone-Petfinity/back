package Capstone.Petfinity.dto.diagnosis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SaveDiagnosisReqDto {

    private String user_uuid;
    private String disease_name;
    private LocalDate date;
    private Double percent;
    private String content;
    private String insert_id;

    public SaveDiagnosisReqDto(String user_uuid, String disease_name, LocalDate date, Double percent, String content, String insert_id) {
        this.user_uuid = user_uuid;
        this.disease_name = disease_name;
        this.date = date;
        this.percent = percent;
        this.content = content;
        this.insert_id = insert_id;
    }
}
