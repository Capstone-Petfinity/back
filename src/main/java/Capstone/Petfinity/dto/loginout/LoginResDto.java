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

    private String uuid;
    private Boolean isParent;
    private String statusCode;
    private String message;

    public LoginResDto(String uuid, Boolean isParent, String statusCode, String message) {
        this.uuid = uuid;
        this.isParent = isParent;
        this.statusCode = statusCode;
        this.message = message;
    }
}
