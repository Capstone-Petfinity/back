package Capstone.Petfinity.dto.vet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class SignupVetReqDto {

    private String auth;
    private String id;
    private String pw;
    private String name;
}
