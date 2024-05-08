package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^(beginner|middle|advance)$", message = "the level start from beginner")
    @Column(columnDefinition = "varchar(20) check (level in ('beginner','middle','advance'))")
    private String level;

    @NotNull(message = "the hours cannot be null")
    @Column(columnDefinition = "int")
    private Integer hours;

    //------------------------------Relation--------------------------------------------------
    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainer")
    private Set<Requests> requests = new HashSet<>();

}
