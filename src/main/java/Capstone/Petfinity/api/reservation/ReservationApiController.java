package Capstone.Petfinity.api.reservation;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.reservation.ReservationReqDto;
import Capstone.Petfinity.exception.*;
import Capstone.Petfinity.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReservationApiController {

    @Autowired
    private final ReservationService reservationService;

    @Value("${auth.key}")
    private String authKey;

    NormalResDto result;

    @PostMapping("/user/reservation")
    public NormalResDto reservation(@RequestHeader("auth") String auth,
                                    @RequestBody ReservationReqDto request) {

        log.info("권한 확인");
        if (!auth.equals(authKey)) {

            log.warn("권한이 없습니다");
            result = new NormalResDto("400", "권한 없음");
            return result;
        }

        log.info("예약 시작");
        try {
            reservationService.reservation(request);

            log.info("예약 성공");
            result = new NormalResDto("200", "예약 성공");
            return result;
        } catch (NullReservationDateException e) {

            result = new NormalResDto("403", "입력되지 않은 예약날짜");
            return result;
        } catch (NullUuidException e) {

            result = new NormalResDto("403", "입력되지 않은 uuid");
            return result;
        } catch (InvalidUuidException e) {

            result = new NormalResDto("401", "유효하지 않은 uuid");
            return result;
        } catch (NotExistException e) {

            result = new NormalResDto("404", "존재하지 않는 회원, 반려동물, 병원");
            return result;
        } catch (NotLoginStatusException e) {

            result = new NormalResDto("406", "로그아웃 상태");
            return result;
        }
    }
}
