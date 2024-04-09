package Capstone.Petfinity.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "address_id")
    private UUID uuid;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Parent parent;

    private String region;

    private String city;
}
