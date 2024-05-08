package com.example.techcare.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@AllArgsConstructor
@Setter
@Getter
public class RequestDTO {
    @Id
    private Integer id;

    @Pattern(regexp = "^(consulting|maintenance|training)$")
    private String type;

    @Pattern(regexp = "^(pending|accepted|rejected)$")
    private String status;

    @PositiveOrZero(message = "rating must be positive or zero")
    private Double rating;

    @DateTimeFormat
    @FutureOrPresent
    @Column(columnDefinition = "date")
    private LocalDate dateOfRequest;

//    @AssertFalse
    private Boolean isCompleted;
}
