package Capstone.Petfinity.api.signup;

import Capstone.Petfinity.dto.address.AddressResDto;
import Capstone.Petfinity.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AddressApiController {

    @Autowired
    private final AddressRepository addressRepository;

    @Value("${auth.key}")
    private String authKey;

    @GetMapping("/address/city")

    public AddressResDto returnCityList(@RequestHeader("auth") String auth) {

        AddressResDto result;

        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            result = new AddressResDto("400", "권한 없음", null);
            return result;
        }

        log.info("도시 리스트 리턴 성공");
        result = new AddressResDto("200", "도시 리턴 성공", addressRepository.findAllCity());
        return result;
    }
}
