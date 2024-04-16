package Capstone.Petfinity.exception;

public class InvalidPhoneNumberException extends IllegalStateException {

    public InvalidPhoneNumberException() {
        super("유효하지 않은 전화번호");
    }
}
