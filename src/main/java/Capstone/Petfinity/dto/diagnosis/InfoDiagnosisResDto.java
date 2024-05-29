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
public class InfoDiagnosisResDto {

    private String statusCode;
    private String message;
    private String disease_name;
    private LocalDate date;
    private String percent;
    private String content;
    private String insert_id;

    public InfoDiagnosisResDto(String statusCode, String message, String disease_name, LocalDate date, String percent, String content, String insert_id) {
        this.statusCode = statusCode;
        this.message = message;
        this.disease_name = disease_name;
        this.date = date;
        this.percent = percent;
        this.content = content;
        this.insert_id = insert_id;
    }
}
