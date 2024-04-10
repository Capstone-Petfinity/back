package Capstone.Petfinity.dto;

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
public class AddressResponseDto {

    private String errorCode;
    private String errorMessage;
    private List<City> cityList;

    public AddressResponseDto(String errorCode, String errorMessage, List<City> cityList) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.cityList = cityList;
    }
}
