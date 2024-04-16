package Capstone.Petfinity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Pet {

    @Id
    @Column(name = "pet_id")
    private String uuid;

    @NotNull
    private String name;
    @NotNull
    private LocalDate birth;
    @NotNull
    private String gender;
    @NotNull
    private String kind;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @JsonIgnore
    @NotNull
    @OneToMany(mappedBy = "pet")
    private List<Reservation> reservations = new ArrayList<>();
}
