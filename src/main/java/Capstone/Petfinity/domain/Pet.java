package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Pet {

    @Id
    @Column(name = "pet_id")
    private String uuid;
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime birth;
    @NotNull
    private String gender;
    @NotNull
    private String kind;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @NotNull
    @OneToMany(mappedBy = "pet")
    private List<Reservation> reservations = new ArrayList<>();
}
