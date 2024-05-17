package Capstone.Petfinity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String userUuid;
    @NotNull
    private LocalDate date;
    @NotNull
    private Double percent;
    @NotNull
    private String content;
    @NotNull
    private byte[] image;
}
