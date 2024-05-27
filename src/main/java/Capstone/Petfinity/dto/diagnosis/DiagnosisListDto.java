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

    private String disease_name;
    private LocalDate date;

    public DiagnosisListDto (String disease_name, LocalDate date){
        this.disease_name = disease_name;
        this.date = date;
    }
}
