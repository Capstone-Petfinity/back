package Capstone.Petfinity.dto.info;

import Capstone.Petfinity.domain.Pet;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class InfoPetsResDto {

    private String statusCode;
    private String message;
    private List<Pet> pets;

    public InfoPetsResDto(String statusCode, String message, List<Pet> pets) {
        this.statusCode = statusCode;
        this.message = message;
        this.pets = pets;
    }
}
