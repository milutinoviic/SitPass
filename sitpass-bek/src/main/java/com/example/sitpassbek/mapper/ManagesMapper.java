package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.manages.ManagesDTO;
import com.example.sitpassbek.model.Manages;
import org.springframework.stereotype.Component;

@Component
public class ManagesMapper {

    public ManagesDTO toDTO(Manages m) {
        ManagesDTO dto = new ManagesDTO();
        dto.setId(m.getId());
        dto.setFacilityId(m.getFacility() != null ? m.getFacility().getId() : null);
        dto.setStartDate(m.getStartDate());
        dto.setEndDate(m.getEndDate());
        dto.setDeleted(m.isDeleted());
        return dto;
    }
}
