package Capstone.Petfinity.dto.reservation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
public class ReservationReqDto {

    private String parentUuid;
    private String petUuid;
    private String hospitalUuid;
    private String reservationDate;
}
