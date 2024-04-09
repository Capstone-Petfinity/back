package Capstone.Petfinity.DTO;

import Capstone.Petfinity.domain.Address;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@Builder
public class LoginParentDTO {

    private String id;
    private String pw;
    private String name;
    private String phone_number;
    private Address address;

    public LoginParentDTO(String id, String pw, String name, String phone_number, Address address) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }
}
