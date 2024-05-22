package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Diagnosis {

    @Id
    @Column(name = "diagnosis_id")
    private String uuid;

    @NotNull
    private String disease_name;
    @NotNull
    @Column(name = "user_id")
    private String user;
    @NotNull
    private LocalDate date;
    @NotNull
    private Double percent;
    @NotNull
    private String content;
    private byte[] image;
}
