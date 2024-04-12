package Capstone.Petfinity.dto.parent;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class IdCheckResponseDto {

    private String statusCode;
    private String message;

    public IdCheckResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
