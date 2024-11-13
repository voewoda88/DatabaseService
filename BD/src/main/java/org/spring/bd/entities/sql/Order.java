package org.spring.bd.entities.sql;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "final_value", nullable = false)
    private BigDecimal finalValue;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "automobile_id", foreignKey = @ForeignKey(name = "automobile_id"))
    private Automobile automobile;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "car_dealership_id", foreignKey = @ForeignKey(name = "car_dealership_id"))
    private CarDealership carDealership;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employer_id", foreignKey = @ForeignKey(name = "id"))
    private Employee employer;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "buyer_id", foreignKey = @ForeignKey(name = "buyer_id"))
    private Buyer buyer;
}
