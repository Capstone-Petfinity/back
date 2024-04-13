package Capstone.Petfinity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class NormalResDto {

    private String statusCode;
    private String message;

    public NormalResDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
