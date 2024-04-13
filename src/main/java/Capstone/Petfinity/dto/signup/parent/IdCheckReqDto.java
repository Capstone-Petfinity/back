package Capstone.Petfinity.dto.signup.parent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class IdCheckReqDto {
    
    String auth;
    String id;
}
