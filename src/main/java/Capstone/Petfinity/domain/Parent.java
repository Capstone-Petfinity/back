package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Parent {

    @Id
    @Column(name = "parent_id")
    private String uuid;
    @NotNull
    private String id;
    @NotNull
    private String pw;
    @NotNull
    private String name;
    @NotNull
    private String phone_number;
    @NotNull
    private Boolean login_status;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @OneToMany(mappedBy = "parent")
    private List<Pet> pets = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "parent")
    private List<Reservation> reservations = new ArrayList<>();
}
