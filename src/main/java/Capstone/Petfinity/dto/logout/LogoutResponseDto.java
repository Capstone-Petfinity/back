package Capstone.Petfinity.dto.logout;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class LogoutResponseDto {

    private String statusCode;
    private String message;

    public LogoutResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
