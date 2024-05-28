package Capstone.Petfinity.dto.diagnosis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DiagnosisListReqDto {

    private String uuid; // user_uuid나 진단 uuid

}
