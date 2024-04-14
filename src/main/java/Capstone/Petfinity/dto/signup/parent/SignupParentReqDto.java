package Capstone.Petfinity.dto.signup.parent;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class SignupParentReqDto {

    private String id;
    private String pw;
    private String name;
    private String phone_number;
    private String city;
}
