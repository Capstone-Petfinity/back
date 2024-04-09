package Capstone.Petfinity.DTO;

import Capstone.Petfinity.domain.Address;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class LoginParentDTO {

    @NotBlank(message = "id는 필수입니다")
    private String id;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotBlank
    private String phone_number;
    @NotBlank
    private String region;
    @NotBlank
    private String city;

    public LoginParentDTO(String id, String pw, String name, String phone_number, String region, String city) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone_number = phone_number;
        this.region = region;
        this.city = city;
    }
}
