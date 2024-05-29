package Capstone.Petfinity.dto.diagnosis;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiagnosisListDto {

    private String uuid;
    private String disease_name;
    private LocalDate date;
    private String percent;
    private String insert_id;
    private String img_url;

    public DiagnosisListDto(String uuid, String disease_name, LocalDate date, String percent, String insert_id) {
        this.uuid = uuid;
        this.disease_name = disease_name;
        this.date = date;
        this.percent = percent;
        this.insert_id = insert_id;
    }
}
