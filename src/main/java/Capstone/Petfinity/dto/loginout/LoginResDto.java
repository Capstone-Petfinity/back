package Capstone.Petfinity.dto.loginout;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class LoginResDto {

    private String statusCode;
    private String message;
    private String uuid;
    private Boolean isParent;

    public LoginResDto(String statusCode, String message, String uuid, Boolean isParent) {
        this.statusCode = statusCode;
        this.message = message;
        this.uuid = uuid;
        this.isParent = isParent;
    }
}
