package Capstone.Petfinity.dto.diagnosis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class DiagnosisDto {

    String user_uuid;
    String disease_name;
    Double percent;
    String content;
    String insert_id;

    public DiagnosisDto(String user_uuid, String disease_name, Double percent, String content, String insert_id) {
        this.user_uuid = user_uuid;
        this.disease_name = disease_name;
        this.percent = percent;
        this.content = content;
        this.insert_id = insert_id;
    }
}
