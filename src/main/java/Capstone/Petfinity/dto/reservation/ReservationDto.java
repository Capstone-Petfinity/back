package Capstone.Petfinity.dto.reservation;

import Capstone.Petfinity.domain.Hospital;
import Capstone.Petfinity.domain.Pet;
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
    private Pet pet;
    private Hospital hospital;
    private LocalDate reservationDate;

    public ReservationDto(String reservationUuid, String parentUuid, Pet pet, Hospital hospital, LocalDate reservationDate) {
        this.reservationUuid = reservationUuid;
        this.parentUuid = parentUuid;
        this.pet = pet;
        this.hospital = hospital;
        this.reservationDate = reservationDate;
    }
}
