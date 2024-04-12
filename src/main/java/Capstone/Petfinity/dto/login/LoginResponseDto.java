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
    private String statusCode;
    private String message;

    public LoginResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    private String uuid;
    private String parentorvet;
}
