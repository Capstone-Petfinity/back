package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.address.AddressRequestDto;
import Capstone.Petfinity.dto.address.AddressResponseDto;
import Capstone.Petfinity.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AddressApiController {

    @Autowired
    AddressRepository addressRepository;

    AddressResponseDto addressResponseDto;
    @PostMapping("/address/city")

    public AddressResponseDto returnCityList(@RequestBody AddressRequestDto addressRequestDto) {
//        log.info("auth 확인");
//        if (!addressRequestDto.getAuth().equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {
//            log.error("권한이 없습니다");
//            addressResponseDto = new AddressResponseDto("401", "권한이 없습니다", null);
//            return addressResponseDto;
//        }
        log.info("Success Return City List");
        addressResponseDto = new AddressResponseDto("200", "Success Return City List", addressRepository.findAllCity());
        return addressResponseDto;
    }
}
