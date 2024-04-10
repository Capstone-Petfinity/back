package Capstone.Petfinity.exception;

import Capstone.Petfinity.exception.signup.InvalidIdException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
/*
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidIdException(InvalidIdException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 아이디");
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<String> handleInvalidPhoneNumberException(InvalidPhoneNumberException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 전화번호");
    }
 */
}
