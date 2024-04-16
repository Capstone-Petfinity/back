package Capstone.Petfinity.dto.info;

import Capstone.Petfinity.domain.Parent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class RegisterPetReqDto {

    private String name;
    private LocalDate birth;
    private String gender;
    private String kind;
    private String parentUuid;
}
