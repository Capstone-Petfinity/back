package Capstone.Petfinity.dto.parent;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SignupParentResponseDto {
    private String statusCode;
    private String message;

    public SignupParentResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
