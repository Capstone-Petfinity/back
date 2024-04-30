package Capstone.Petfinity.dto.info.hospital;

import Capstone.Petfinity.domain.Hospital;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class InfoHospitalResDto {

    private String statusCode;
    private String message;
    private String hospital_name;
    private String hospital_callnumber;
    private LocalTime open_time;
    private LocalTime close_time;
    private LocalTime lunch_start;
    private LocalTime lunch_finish;
    private String address;
    private String city;

    public InfoHospitalResDto(String statusCode, String message, String hospital_name, String hospital_callnumber, LocalTime open_time, LocalTime close_time, LocalTime lunch_start, LocalTime lunch_finish, String address, String city) {
        this.statusCode = statusCode;
        this.message = message;
        this.hospital_name = hospital_name;
        this.hospital_callnumber = hospital_callnumber;
        this.open_time = open_time;
        this.close_time = close_time;
        this.lunch_start = lunch_start;
        this.lunch_finish = lunch_finish;
        this.address = address;
        this.city = city;
    }
}
