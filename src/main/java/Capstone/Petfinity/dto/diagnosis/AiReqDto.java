package Capstone.Petfinity.dto.diagnosis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AiReqDto {

    private String user_uuid;
    private String user_type;
    private String disease_area;
    private String type;
    private String position;
    private String detail_area;
    private String disease;
    private String insert_id;
}
