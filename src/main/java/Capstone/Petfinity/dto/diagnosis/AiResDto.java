package Capstone.Petfinity.dto.diagnosis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class AiResDto {

    private String statusCode;
    private String message;
    private String user_uuid;
    private String disease_name;
    private String percent;
    private String content;
    private String insert_id;

    public AiResDto(String statusCode, String message, String user_uuid, String disease_name, String percent, String content, String insert_id) {
        this.statusCode = statusCode;
        this.message = message;
        this.user_uuid = user_uuid;
        this.disease_name = disease_name;
        this.percent = percent;
        this.content = content;
        this.insert_id = insert_id;
    }
}
