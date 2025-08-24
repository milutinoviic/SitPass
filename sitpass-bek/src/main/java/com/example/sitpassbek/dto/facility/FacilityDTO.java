package com.example.sitpassbek.dto.facility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacilityDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String city;
    private Double totalRating;
    private LocalDate createdAt;
    private Boolean active;
}
