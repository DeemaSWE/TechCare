package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @FutureOrPresent
    @Column(columnDefinition = "date")
    private LocalDate date;

    @Pattern(regexp = "^(processing|shipped|delivered)$")
    @Column(columnDefinition = "varchar(15) check(status='processing' or status='shipped' or status='delivered')")
    private String status;

    @Positive(message = "total price must be positive number")
    @Column(columnDefinition = "double")
    private Double totalPrice;

    @Column(columnDefinition = "varchar(200)")
    private String shippingAddress;

    //----------------------------------------Relation----------------------------------------

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
