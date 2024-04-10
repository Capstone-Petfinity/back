package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private String city;
    @NotNull
    private Boolean login_status;

    @OneToMany(mappedBy = "parent")
    private List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<Reservation> reservations = new ArrayList<>();
}
