package com.example.techcare.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServicesDTO {

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotEmpty(message = "Service type cannot be empty")
    @Pattern(regexp = "^(consulting|maintenance|training)$")
    private String type;

    @Positive(message = "Training hours must be positive number")
    private Integer hours;

    @NotNull(message = "Category id cannot be empty")
    @Positive(message = "Category id must be positive")
    private Integer categoryId;
}
