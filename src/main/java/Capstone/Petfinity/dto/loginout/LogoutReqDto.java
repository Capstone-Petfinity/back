package Capstone.Petfinity.dto.loginout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LogoutReqDto {

    private String uuid;
    private Boolean isParent;
}
