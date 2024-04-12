package Capstone.Petfinity.dto.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class LoginResponseDto {
    private String uuid;
    private String who;
    private String statusCode;
    private String message;

    public LoginResponseDto(String uuid, String who, String statusCode, String message) {
        this.uuid = uuid;
        this.who = who;
        this.statusCode = statusCode;
        this.message = message;
    }
}
