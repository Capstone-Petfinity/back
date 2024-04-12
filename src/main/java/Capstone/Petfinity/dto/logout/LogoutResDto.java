package Capstone.Petfinity.dto.logout;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class LogoutResDto {

    private String statusCode;
    private String message;

    public LogoutResDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
