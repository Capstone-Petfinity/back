package Capstone.Petfinity.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SignupParentResponseDto {
    private String errorCode;
    private String errorMessage;

    public SignupParentResponseDto(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
