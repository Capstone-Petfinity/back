package Capstone.Petfinity.dto.diagnosis;

import java.time.LocalDate;

public class DiagnosisListDto {

    private String disease_name;

    private LocalDate date;

    public DiagnosisListDto (String disease_name, LocalDate date){
        this.disease_name = disease_name;
        this.date = date;
    }
}
