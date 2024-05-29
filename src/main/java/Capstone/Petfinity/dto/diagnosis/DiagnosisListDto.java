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
public class DiagnosisListDto {

    private String uuid;
    private String disease_name;
    private LocalDate date;
    private String percent;
    private String insert_id;

    public DiagnosisListDto(String uuid, String disease_name, LocalDate date, String percent, String insert_id) {
        this.uuid = uuid;
        this.disease_name = disease_name;
        this.date = date;
        this.percent = percent;
        this.insert_id = insert_id;
    }
}
