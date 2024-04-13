package Capstone.Petfinity.dto.logout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class LogoutReqDto {
    private String auth;
    private String uuid;
    private Boolean isParent;
}
