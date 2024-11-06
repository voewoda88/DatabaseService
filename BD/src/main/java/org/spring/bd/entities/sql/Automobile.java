package org.spring.bd.entities.sql;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "automobile")
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automobile_id", nullable = false)
    private Integer id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "generation", nullable = false)
    private String generation;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "engine_volume", precision = 4, scale = 1)
    private BigDecimal engineVolume;

    @Column(name = "gearbox", nullable = false)
    private String gearbox;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "engine", nullable = false)
    private String engine;

    @Column(name = "drive", nullable = false)
    private String drive;

    @Column(name = "power", nullable = false)
    private int power;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_dealership_has_automobiles",
            joinColumns = @JoinColumn(name = "automobile_id"),
            inverseJoinColumns = @JoinColumn(name = "car_dealership_id")
    )
    private Set<CarDealership> carDealerships = new HashSet<>();
}
