package com.example.techcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.BadLocationException;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    private Integer id;

    @NotEmpty(message = "address cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String address;

    @NotNull (message = "balance cannot be empty")
    @Column(columnDefinition = "double")
    private Double balance;


    //----------------------------------------Relation----------------------------------------

    @OneToOne
    @JsonIgnore
    @MapsId
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Requests> requests = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Orders> orders = new HashSet<>();

}

