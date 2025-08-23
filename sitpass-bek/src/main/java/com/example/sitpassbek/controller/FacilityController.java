package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.facility.CreateFacilityDTO;
import com.example.sitpassbek.dto.facility.FacilityDTO;
import com.example.sitpassbek.dto.facility.UpdateFacilityDTO;
import com.example.sitpassbek.service.FacilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<FacilityDTO> createFacility(@Valid @RequestBody CreateFacilityDTO dto) {
        FacilityDTO facilityDTO = facilityService.createFacility(dto);
        return ResponseEntity.ok(facilityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityDTO> updateFacility(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFacilityDTO dto) {

        FacilityDTO updatedFacility = facilityService.updateFacility(id, dto);
        return ResponseEntity.ok(updatedFacility);
    }

}
