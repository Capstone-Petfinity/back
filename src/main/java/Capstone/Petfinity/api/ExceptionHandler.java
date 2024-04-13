package Capstone.Petfinity.api;

import Capstone.Petfinity.dto.NormalResDto;
import Capstone.Petfinity.dto.logout.LogoutResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@Slf4j
@RestControllerAdvice
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
    NormalResDto result;

    @org.springframework.web.bind.annotation.ExceptionHandler({
            IllegalStateException.class, IllegalArgumentException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class, MultipartException.class,
    })
    public NormalResDto handleBadRequestException(Exception e) {
        result = new NormalResDto("404", "로그아웃 실패");
        return result;
    }
}
