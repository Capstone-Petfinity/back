package Capstone.Petfinity.dto.info;

import Capstone.Petfinity.domain.Pet;
import Capstone.Petfinity.domain.Reservation;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class InfoParentResDto {

    private String statusCode;
    private String message;
    private String uuid;
    private String id;
    private String name;
    private String phone_number;
    private String city;
    private List<Reservation> reservations;

    public InfoParentResDto(String statusCode, String message, String uuid, String id, String name, String phone_number, String city, List<Reservation> reservations) {
        this.statusCode = statusCode;
        this.message = message;
        this.uuid = uuid;
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.city = city;
        this.reservations = reservations;
    }
}
