package Capstone.Petfinity.dto.info;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class InfoVetResDto {

    private String statusCode;
    private String message;
    private String uuid;
    private String id;
    private String name;

    public InfoVetResDto(String statusCode, String message, String uuid, String id, String name) {
        this.statusCode = statusCode;
        this.message = message;
        this.uuid = uuid;
        this.id = id;
        this.name = name;
    }
}
