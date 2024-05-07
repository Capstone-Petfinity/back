package Capstone.Petfinity.dto.signup.parent;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class SignupParentReqDto {

    private String id;
    private String pw;
    private String name;
    private String phone_number;
    private String city;

    public SignupParentReqDto(String id, String pw, String name, String phone_number, String city) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone_number = phone_number;
        this.city = city;
    }
}
