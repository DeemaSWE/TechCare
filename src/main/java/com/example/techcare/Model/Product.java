package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be positive number")
    private Double price;

    @PositiveOrZero(message = "Average rating must be positive or zero")
    @Column(columnDefinition = "double default 0")
    private Double avgRating;

    @PositiveOrZero(message = "Rating count must be positive or zero")
    @Column(columnDefinition = "integer default 0")
    private Integer ratingCount;

    @NotEmpty(message = "Image URL cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String imageUrl;

    @NotNull(message = "Stock cannot be empty")
    @PositiveOrZero(message = "Stock must be positive number")
    @Column(columnDefinition = "integer")
    private Integer stock;


    //----------------------------------------Relation----------------------------------------

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private Category category;
}
