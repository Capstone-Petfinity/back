package Capstone.Petfinity.dto.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class ReservationDto {

    private String reservationUuid;
    private String parentUuid;
    private String petUuid;
    private String hospitalUuid;
    private LocalDate reservationDate;

    public ReservationDto(String reservationUuid, String parentUuid, String petUuid, String hospitalUuid, LocalDate reservationDate) {
        this.reservationUuid = reservationUuid;
        this.parentUuid = parentUuid;
        this.petUuid = petUuid;
        this.hospitalUuid = hospitalUuid;
        this.reservationDate = reservationDate;
    }
}
