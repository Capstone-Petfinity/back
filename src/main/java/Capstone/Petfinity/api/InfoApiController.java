package Capstone.Petfinity.api;

import Capstone.Petfinity.domain.Parent;
import Capstone.Petfinity.dto.info.InfoParentReqDto;
import Capstone.Petfinity.dto.info.InfoParentResDto;
import Capstone.Petfinity.exception.InvalidUuidException;
import Capstone.Petfinity.exception.NotExistException;
import Capstone.Petfinity.exception.NotLoginStatusException;
import Capstone.Petfinity.exception.NullUuidException;
import Capstone.Petfinity.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InfoApiController {

    @Autowired
    private final ParentService parentService;

    InfoParentResDto result;

    @PostMapping("/user/info/parent")
    public InfoParentResDto infoParent(@RequestHeader("auth") String auth,
                                       @RequestBody InfoParentReqDto request) {
        log.debug("권한 확인");
        if (!auth.equals("bVAtkPtiVGpWuO3dWEnvr51cEb6r7oF8")) {

            log.warn("권한이 없습니다");
            result = new InfoParentResDto("400", "권한 없음", null, null, null, null, null, null, null);
            return result;
        }

        log.debug("보호자 정보 조회 확인");
        try {
            Parent parent = parentService.infoParent(request);

            result = new InfoParentResDto("200", "회원 정보 조회 성공", parent.getUuid(), parent.getId(), parent.getPw(), parent.getName(), parent.getPhone_number(), parent.getCity(), null);
            return result;
        } catch (NotLoginStatusException e) {

            result = new InfoParentResDto("406", "로그아웃 상태", null, null, null, null, null, null, null);
            return result;
        } catch (NullUuidException e) {

            result = new InfoParentResDto("403", "입력되지 않은 uuid", null, null, null, null, null, null, null);
            return result;
        } catch (InvalidUuidException e) {
            result = new InfoParentResDto("401", "유효하지 않는 uuid", null, null, null, null, null, null, null);
            return result;
        } catch (NotExistException e) {

            result = new InfoParentResDto("404", "존재하지 않는 회원", null, null, null, null, null, null, null);
            return result;
        }
    }
}
