package Capstone.Petfinity.DTO;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class LoginParentDTO {

    private String id;
    private String pw;
    private String name;
    private String phone_number;
    private String city;
}
