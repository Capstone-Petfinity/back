package Capstone.Petfinity.api;

import Capstone.Petfinity.domain.City;
import Capstone.Petfinity.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AddressApiController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/address/city")
    public List<City> returnCityList() {
        log.info("Success Return City List");
        return addressRepository.findAllCity();
    }
}
