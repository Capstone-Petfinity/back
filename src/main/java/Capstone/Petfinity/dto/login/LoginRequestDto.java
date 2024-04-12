package Capstone.Petfinity.dto.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LoginRequestDto {
    private String auth;
    private String id;
    private String pw;
}
