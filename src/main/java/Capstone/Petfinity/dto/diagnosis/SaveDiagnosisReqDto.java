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

    private String uuid;
    private String disease_name;
    private String userUuid;
    private LocalDate date;
    private Double percent;
    private String content;
    private byte[] image;

    public SaveDiagnosisReqDto(String uuid, String disease_name, String userUuid, LocalDate date, Double percent, String content, byte[] image) {
        this.uuid = uuid;
        this.disease_name = disease_name;
        this.userUuid = userUuid;
        this.date = date;
        this.percent = percent;
        this.content = content;
        this.image = image;
    }
}
