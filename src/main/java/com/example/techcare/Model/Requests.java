package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Requests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^(consulting|maintenance|training)$")
    @Column(columnDefinition = "varchar(15) not null check(type='consulting' or type='maintenance' or type='training')")
    private String type;

    @Pattern(regexp = "^(pending|accepted|rejected)$")
    @Column(columnDefinition = "varchar(15) not null check(status='pending' or status='accepted' or status='rejected')")
    private String status;

    @PositiveOrZero(message = "rating must be positive or zero")
    @Column(columnDefinition = "double default 0")
    private Double rating;

    @DateTimeFormat
    @FutureOrPresent
    @Column(columnDefinition = "date")
    private LocalDate dateOfRequest;

    @Column(columnDefinition = "boolean default false")
    private Boolean isCompleted;


    //----------------------------------------Relation----------------------------------------

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id" ,referencedColumnName = "user_id")
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    private Technician technician;

    @ManyToOne
    @JsonIgnore
    private Trainer trainer ;

    @OneToOne
    @JsonIgnore
    @MapsId
    private Services service;



}
