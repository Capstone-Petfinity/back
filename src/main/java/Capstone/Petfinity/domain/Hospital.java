package Capstone.Petfinity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Hospital {

    @Id
    @Column(name = "hospital_id")
    private String uuid;

    @NotNull
    private String hospital_name;
    @NotNull
    private String hospital_callnumber;
    @NotNull
    private LocalDateTime open_time;
    @NotNull
    private LocalDateTime close_time;
    @NotNull
    private LocalDateTime lunch_start;
    @NotNull
    private LocalDateTime lunch_finish;
    @NotNull
    private String city;
}
