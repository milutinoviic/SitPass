package com.example.sitpassbek.dto.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {

    private Long id;
    private Long facilityId;
    private Long userId;
    private LocalDateTime from;
    private LocalDateTime until;
}
