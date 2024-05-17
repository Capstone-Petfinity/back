package Capstone.Petfinity.dto.info.diagnosis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
public class SaveDiagnosisReqDto {

    private String uuid;
    private String disease_name;
    private String parent_uuid;
    private LocalDate date;
    private Double percent;
    private String content;
    private Byte[] image;
}
