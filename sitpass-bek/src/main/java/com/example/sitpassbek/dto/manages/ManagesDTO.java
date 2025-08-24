package com.example.sitpassbek.dto.manages;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagesDTO {
    private Long id;
    private Long facilityId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isDeleted;
}