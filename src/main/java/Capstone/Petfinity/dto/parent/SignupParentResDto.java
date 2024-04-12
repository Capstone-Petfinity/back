package Capstone.Petfinity.dto.parent;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class SignupParentResDto {
    private String statusCode;
    private String message;

    public SignupParentResDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
