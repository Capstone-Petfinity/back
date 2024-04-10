package Capstone.Petfinity.dto.parent;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class SignupParentRequestDto {

    private String auth;
    private String id;
    private String pw;
    private String name;
    private String phone_number;
    private String city;
}
