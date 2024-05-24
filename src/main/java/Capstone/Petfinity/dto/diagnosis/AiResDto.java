package Capstone.Petfinity.dto.diagnosis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class AiResDto {

    private String statusCode;
    private String message;
    String userUuid;
    String disease_name;
    Double percent;
    String content;

    public AiResDto(String statusCode, String message, String userUuid, String disease_name, Double percent, String content) {
        this.statusCode = statusCode;
        this.message = message;
        this.userUuid = userUuid;
        this.disease_name = disease_name;
        this.percent = percent;
        this.content = content;
    }
}
