package com.example.sitpassbek.dto.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PastVisitsRequestDTO {
    private Long facilityId;
    private Long userId;
}
