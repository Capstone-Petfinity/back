package Capstone.Petfinity.dto.address;

import Capstone.Petfinity.domain.City;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class AddressResDto {

    private String statusCode;
    private String message;
    private List<City> cityList;

    public AddressResDto(String statusCode, String message, List<City> cityList) {
        this.statusCode = statusCode;
        this.message = message;
        this.cityList = cityList;
    }
}
