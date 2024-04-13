package Capstone.Petfinity.api.signup;

import Capstone.Petfinity.dto.address.AddressResDto;
import Capstone.Petfinity.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AddressApiController {

    @Autowired
    private final AddressRepository addressRepository;

    @GetMapping("/address/city")

    public AddressResDto returnCityList(@RequestHeader("auth") String auth) {

        AddressResDto result;

        log.debug("Auth Check");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.error("No Authorization");
            result = new AddressResDto("400", "권한 없음", null);
            return result;
        }

        log.debug("Return City List Success");
        result = new AddressResDto("200", "도시 리턴 성공", addressRepository.findAllCity());
        return result;
    }
}
