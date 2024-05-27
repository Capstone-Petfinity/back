package Capstone.Petfinity.dto.diagnosis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AiReqDto {

    private String user_uuid;
    private String disease_area;
    private String type;
    private String position;
    private String disease;
    private String img_url;
}
