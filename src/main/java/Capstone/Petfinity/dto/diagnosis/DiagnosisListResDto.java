package Capstone.Petfinity.dto.diagnosis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class DiagnosisListResDto {

    private String statusCode;
    private String message;
    private List<DiagnosisListDto> diagnoses;

    public DiagnosisListResDto(String statusCode, String message, List<DiagnosisListDto> diagnoses) {
        this.statusCode = statusCode;
        this.message = message;
        this.diagnoses = diagnoses;
    }
}
