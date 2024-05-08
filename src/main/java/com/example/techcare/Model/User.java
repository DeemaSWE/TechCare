package com.example.techcare.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class
User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 10, message = "Username must be between 4 and 10 characters")
    @Column(columnDefinition = "varchar(10) not null check (length(username) >= 3 and length(username) <= 10)", unique = true)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(columnDefinition = "varchar(60) not null")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null check (length(name) >= 2 and length(name) <= 20)")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be in valid format")
    @Column(columnDefinition = "varchar(100)", unique = true)
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be followed by 8 digits")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;

    @Pattern(regexp = "^(TECHNICIAN|TRAINER|CUSTOMER|ADMIN)$", message = "Role must be either CUSTOMER or TECHNICIAN or or TRAINER or ADMIN")
    @Column(columnDefinition = "varchar(10) check (role in ('CUSTOMER','TECHNICIAN','TRAINER','ADMIN'))")
    private String role;

    @NotEmpty(message = "gender cannot be empty")
    @Pattern(regexp = "^(male|female)$", message = "Gender must be female or male")
    @Column(columnDefinition = "varchar(6) check(gender='male' or gender='female')")
    private String gender;

    @NotNull(message = "age cannot be empty")
    @Min(value = 18,message = "the minimum age to register is 18")
    private Integer age;

    //--------------------------------Relation--------------------------------------------------
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Technician technician;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Trainer trainer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
