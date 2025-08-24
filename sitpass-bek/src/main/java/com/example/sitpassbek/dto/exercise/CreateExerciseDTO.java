package com.example.sitpassbek.dto.exercise;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateExerciseDTO {

    @NotNull(message = "User ID must not be null")
    private Long userId;

    @NotNull(message = "Facility ID must not be null")
    private Long facilityId;

    @NotNull(message = "Start time must not be null")
    private LocalDateTime from;

    @NotNull(message = "End time must not be null")
    private LocalDateTime until;
}
