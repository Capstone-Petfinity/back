package Capstone.Petfinity.api;

import Capstone.Petfinity.DTO.LoginParentDTO;
import Capstone.Petfinity.exception.signup.*;
import Capstone.Petfinity.service.ParentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ParentApiController {

    @Autowired
    private final ParentService parentService;

    @PostMapping("/user/signup/parent")
    public ResponseEntity<?> signupParent(@RequestBody LoginParentDTO request) {

        try {
            log.info("start signup");
            parentService.signup(request);
            return ResponseEntity.ok("Login successful");
        } catch (InvalidIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 아이디");
        } catch (InvalidPhoneNumberException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 전화번호");
        } catch (DuplicateIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 아이디");
        } catch (DuplicatePhoneNumberException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 전화번호");
        } catch (NullNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이름 공백");
        } catch (NullPwException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 공백");
        }
    }
}
