package Capstone.Petfinity.dto.vet;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SignupVetResponseDto {
    private String statusCode;
    private String errorMessage;

    public SignupVetResponseDto(String statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}