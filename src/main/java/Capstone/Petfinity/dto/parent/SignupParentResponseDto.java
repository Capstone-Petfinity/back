package Capstone.Petfinity.dto.parent;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SignupParentResponseDto {
    private String statusCode;
    private String errorMessage;

    public SignupParentResponseDto(String errorCode, String errorMessage) {
        this.statusCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
