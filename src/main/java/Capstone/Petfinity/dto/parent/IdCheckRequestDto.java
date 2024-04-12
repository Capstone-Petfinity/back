package Capstone.Petfinity.dto.parent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class IdCheckRequestDto {
    
    String auth;
    String id;
}
