package Capstone.Petfinity.dto.parent;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class IdCheckResDto {

    private String statusCode;
    private String message;

    public IdCheckResDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
