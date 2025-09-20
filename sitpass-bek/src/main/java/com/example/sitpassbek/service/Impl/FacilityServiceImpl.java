package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.facility.CreateFacilityDTO;
import com.example.sitpassbek.dto.facility.FacilityDTO;
import com.example.sitpassbek.dto.facility.UpdateFacilityDTO;
import com.example.sitpassbek.mapper.FacilityMapper;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;
    private final FacilityMapper facilityMapper;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository, FacilityMapper facilityMapper) {
        this.facilityRepository = facilityRepository;
        this.facilityMapper = facilityMapper;
    }

    @Override
    public void deleteFacility(Long facilityId) {
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found or already deleted"));

        facility.setDeleted(true);
        facilityRepository.save(facility);
    }

    @Override
    public FacilityDTO createFacility(CreateFacilityDTO dto) {

        Facility facility = new Facility();
        facility.setName(dto.getName());
        facility.setDescription(dto.getDescription());
        facility.setAddress(dto.getAddress());
        facility.setCity(dto.getCity());
        facility.setCreatedAt(LocalDate.now());
        facility.setActive(true);
        facility.setDeleted(false);

        Facility saved = facilityRepository.save(facility);
        return facilityMapper.toDTO(saved);
    }

    @Override
    public FacilityDTO updateFacility(Long id, UpdateFacilityDTO dto) {
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Facility not found or deleted"));

        if (dto.getName() != null) facility.setName(dto.getName());
        if (dto.getDescription() != null) facility.setDescription(dto.getDescription());
        if (dto.getAddress() != null) facility.setAddress(dto.getAddress());
        if (dto.getCity() != null) facility.setCity(dto.getCity());

        Facility updated = facilityRepository.save(facility);
        return facilityMapper.toDTO(updated);
    }

    @Override
    public FacilityDTO getFacility(Long id) {
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Facility not found or deleted"));

        return facilityMapper.toDTO(facility);
    }

    @Override
    public List<FacilityDTO> getAllFacilityActiv(){

        List<Facility> facilities = facilityRepository.findAllByIsDeletedFalseAndActiveTrue();
        return facilityMapper.toDTOList(facilities);
    }

}

