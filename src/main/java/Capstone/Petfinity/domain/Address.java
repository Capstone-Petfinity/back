package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @Column(name = "address_id")
    private String uuid;;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    @NotNull
    private Parent parent;
    @NotNull
    private String region;
    @NotNull
    private String city;
}
