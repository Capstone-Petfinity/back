package Capstone.Petfinity.exception;

public class InvalidPhoneNumberException extends IllegalStateException {

    public InvalidPhoneNumberException() {
        super("유효하지 않는 전화번호");
    }
}
