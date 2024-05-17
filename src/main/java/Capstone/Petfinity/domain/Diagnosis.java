package Capstone.Petfinity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Diagnosis {

    @Id
    @Column(name = "diagnosis_id")
    private String uuid;

    @NotNull
    private String disease_name;
    @NotNull
    private String parent_uuid;
    @NotNull
    private LocalDate date;
    @NotNull
    private Double percent;
    @NotNull
    private String content;
    @NotNull
    private byte[] image;
}
