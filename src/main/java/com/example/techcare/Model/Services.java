package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be positive")
    @Column(columnDefinition = "double")
    private Double price;

    @NotEmpty(message = "Service type cannot be empty")
    @Pattern(regexp = "^(consulting|maintenance|training)$", message = "Service type must be consulting, maintenance or training")
    @Column(columnDefinition = "varchar(15) not null check(type='consulting' or type='maintenance' or type='training')")
    private String type;

    @PositiveOrZero(message = "Average rating must be positive or zero")
    @Column(columnDefinition = "double default 0")
    private Double avgRating;

    @PositiveOrZero(message = "Rating count must be positive or zero")
    @Column(columnDefinition = "integer default 0")
    private Integer ratingCount;

    @Positive(message = "Training hours must be positive number")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hours;

    //----------------------------------------Relation----------------------------------------

    @ManyToOne
    @JsonIgnore
    private Technician technician;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToOne (cascade = CascadeType.DETACH,mappedBy = "service")
    @PrimaryKeyJoinColumn
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Requests request;

}
