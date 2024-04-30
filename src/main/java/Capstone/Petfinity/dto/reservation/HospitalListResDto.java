package Capstone.Petfinity.dto.info.hospital;

import Capstone.Petfinity.domain.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class HospitalListResDto {

    private String statusCode;
    private String message;
    private List<Hospital> hospitalList;

    public HospitalListResDto(String statusCode, String message, List<Hospital> hospitalList) {
        this.statusCode = statusCode;
        this.message = message;
        this.hospitalList = hospitalList;
    }
}
