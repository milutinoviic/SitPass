package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.facility.CreateFacilityDTO;
import com.example.sitpassbek.dto.facility.FacilityDTO;
import com.example.sitpassbek.dto.facility.UpdateFacilityDTO;

public interface FacilityService {

    FacilityDTO createFacility(CreateFacilityDTO dto);

    FacilityDTO updateFacility(Long id, UpdateFacilityDTO dto);

    void deleteFacility(Long facilityId);
}
