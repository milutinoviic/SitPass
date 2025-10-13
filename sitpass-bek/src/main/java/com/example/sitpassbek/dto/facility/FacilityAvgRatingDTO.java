package com.example.sitpassbek.dto.facility;

import lombok.Data;

@Data
public class FacilityAvgRatingDTO {

    private Long facilityId;

    private Double avgEquipment;

    private Double avgHygene;

    private Double avgSpace;

    private Double avgStaff;

}
