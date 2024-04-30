package Capstone.Petfinity.dto.reservation;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.domain.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class InfoReservationResDto {

    private String statusCode;
    private String message;
    private List<ReservationDto> reservations;

    public InfoReservationResDto(String statusCode, String message, List<ReservationDto> reservations) {
        this.statusCode = statusCode;
        this.message = message;
        this.reservations = reservations;
    }
}
