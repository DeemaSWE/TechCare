package com.example.techcare.DTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CustomerDTO {

    @Id
    private Integer id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be in valid format")
    private String email;

    @NotNull(message = "age must not be empty")
    @Min(value = 18, message = "minimum age should be 18")
    private Integer age;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be followed by 8 digits")
    private String phoneNumber;

    @NotEmpty(message = "Gender must not be empty")
    @Pattern(regexp = "^(male|female)$", message = "Gender must be female or male")
    private String gender;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @NotNull(message = "balance cannot be empty")
    @PositiveOrZero(message = "balance must be positive number")
    private Double balance;
}
