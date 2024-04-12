package Capstone.Petfinity.dto.vet;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SignupVetResDto {
    private String statusCode;
    private String message;

    public SignupVetResDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}