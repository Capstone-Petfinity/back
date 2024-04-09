package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
