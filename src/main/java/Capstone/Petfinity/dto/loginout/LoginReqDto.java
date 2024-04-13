package Capstone.Petfinity.dto.loginout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LoginReqDto {

    private String auth;
    private String id;
    private String pw;
}
