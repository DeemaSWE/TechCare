package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
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
public class Technician {

    @Id
    private Integer id;

    @PositiveOrZero(message = "Average rating must be positive or zero")
    @Column(columnDefinition = "double default 0")
    private Double avgRating;

    @PositiveOrZero(message = "Rating count must be positive or zero")
    @Column(columnDefinition = "integer default 0")
    private Integer ratingCount;

    @NotNull(message = "Years of experience cannot be empty")
    @Positive(message = "Years of experience must be positive")
    @Column(columnDefinition = "integer")
    private Integer yearsOfExperience;

    @NotEmpty(message = "Certificate cannot be empty")
    private String certificateSCE;


    //----------------------------------------Relation----------------------------------------

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technician")
    private Set<Services> services = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "technician")
    private Set<Requests> requests = new HashSet<>();

}
