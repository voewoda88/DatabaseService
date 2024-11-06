package org.spring.bd.entities.sql;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_dealership")
public class CarDealership {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "car_dealeship_id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "working_time", nullable = false)
    private String workingTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_dealership_has_automobiles",
            joinColumns = @JoinColumn(name = "car_dealership_id"),
            inverseJoinColumns = @JoinColumn(name = "automobile_id")
    )
    private Set<Automobile> automobiles = new HashSet<>();
}
