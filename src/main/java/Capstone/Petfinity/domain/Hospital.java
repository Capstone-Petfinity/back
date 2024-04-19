package Capstone.Petfinity.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private LocalTime open_time;
    @NotNull
    private LocalTime close_time;
    @NotNull
    private LocalTime lunch_start;
    @NotNull
    private LocalTime lunch_finish;
    @NotNull
    private String city;
}
