package com.example.techcare.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemDTO {

    @NotNull(message = "Quantity cannot be empty")
    @Positive(message = "Quantity must be positive number")
    private Integer quantity;

    @NotNull(message = "Order id cannot be empty")
    @Positive(message = "Product id must be positive number")
    private Integer productId;

}
