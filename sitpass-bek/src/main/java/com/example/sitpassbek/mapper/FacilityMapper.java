package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.facility.FacilityDTO;
import com.example.sitpassbek.model.Facility;
import org.springframework.stereotype.Component;

@Component
public class FacilityMapper {

    public static FacilityDTO toDTO(Facility facility) {
        return new FacilityDTO(
                facility.getId(),
                facility.getName(),
                facility.getDescription(),
                facility.getAddress(),
                facility.getCity(),
                facility.getTotalRating(),
                facility.getCreatedAt(),
                facility.getActive()
        );
    }
}
