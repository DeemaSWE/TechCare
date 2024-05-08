package com.example.techcare.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TechnicianDTO {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 characters")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be in valid format")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and be followed by 8 digits")
    private String phoneNumber;

    @NotNull(message = "age must not be empty")
    @Min(value = 18, message = "minimum age should be 18")
    private Integer age;

    @NotEmpty(message = "Gender must not be empty")
    @Pattern(regexp = "^(male|female)$", message = "Gender must be female or male")
    private String gender;

    @PositiveOrZero(message = "Average rating must be positive or zero")
    private Double avgRating;

    @PositiveOrZero(message = "Rating count must be positive or zero")
    private Integer ratingCount;

    @NotNull(message = "Years of experience cannot be empty")
    @Positive(message = "Years of experience must be positive")
    private Integer yearsOfExperience;

    @NotEmpty(message = "Certificate cannot be empty")
    private String certificateSCE;
}
