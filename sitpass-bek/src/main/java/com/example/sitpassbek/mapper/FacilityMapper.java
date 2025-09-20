package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.facility.FacilityDTO;
import com.example.sitpassbek.model.Facility;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<FacilityDTO> toDTOList(List<Facility> facilities) {
        if (facilities == null) {
            return Collections.emptyList();
        }
        return facilities.stream()
                .map(FacilityMapper::toDTO)
                .collect(Collectors.toList());
    }
}
