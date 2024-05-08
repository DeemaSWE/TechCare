package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull(message = "quantity cannot be empty")
    @Positive(message = "quantity must be positive number")
    @Column(columnDefinition = "integer")
    private Integer quantity;

    //----------------------------------------Relation----------------------------------------

    @ManyToOne
    @JsonIgnore
    private Orders order;

    @ManyToOne
    @JsonIgnore
    private Product product;
}
