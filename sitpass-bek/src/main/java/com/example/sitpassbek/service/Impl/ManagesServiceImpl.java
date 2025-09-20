package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.manages.CheckManageDTO;
import com.example.sitpassbek.dto.manages.CreateManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDTO;
import com.example.sitpassbek.dto.manages.ManagesDetailDTO;
import com.example.sitpassbek.mapper.ManagesMapper;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.model.Manages;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.repository.ManagesRepository;
import com.example.sitpassbek.repository.UserRepository;
import com.example.sitpassbek.service.ManagesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ManagesServiceImpl implements ManagesService {

    private final ManagesRepository managesRepo;
    private final UserRepository userRepo;
    private final FacilityRepository facilityRepo;
    private final ManagesMapper mapper;
    private final ManagesMapper managesMapper;

    public ManagesServiceImpl(ManagesRepository managesRepo, UserRepository userRepo, FacilityRepository facilityRepo, ManagesMapper mapper, ManagesMapper managesMapper) {
        this.managesRepo = managesRepo;
        this.userRepo = userRepo;
        this.facilityRepo = facilityRepo;
        this.mapper = mapper;
        this.managesMapper = managesMapper;
    }

    @Override
    public ManagesDTO assignManager(CreateManagesDTO createManagesDTO) {

        User user = userRepo.findByIdAndIsDeletedFalse(createManagesDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Facility facility = facilityRepo.findByIdAndIsDeletedFalse(createManagesDTO.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        managesRepo.findByUserIdAndFacilityIdAndIsDeletedFalse(
                createManagesDTO.getUserId(),
                createManagesDTO.getFacilityId()
        ).ifPresent(m -> {
            throw new RuntimeException("Manager for this facility already exists");
        });

        Manages manages = new Manages();
        manages.setStartDate(LocalDate.now());
        manages.setEndDate(LocalDate.now().plus(1, ChronoUnit.YEARS));
        manages.setDeleted(false);
        manages.setFacility(facility);
        manages.setUser(user);

        Manages saved = managesRepo.save(manages);
        facility.setActive(true);
        facilityRepo.save(facility);
        return mapper.toDTO(saved);
    }

    @Override
    public void deleteManages(CreateManagesDTO createManagesDTO) {

        Manages manages = managesRepo.findByUserIdAndFacilityIdAndIsDeletedFalse(
                createManagesDTO.getUserId(),
                createManagesDTO.getFacilityId()
        ).orElseThrow(() -> new RuntimeException("Active Manages entry not found"));

        Facility facility = facilityRepo.findByIdAndIsDeletedFalse(createManagesDTO.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        manages.setDeleted(true);
        managesRepo.save(manages);
        facility.setActive(false);
        facilityRepo.save(facility);
    }

    @Override
    public List<ManagesDetailDTO> getActiveManagersByFacility(Long facilityId) {
        List<Manages> manages = managesRepo.findAllByFacilityIdAndIsDeletedFalse(facilityId);
        return managesMapper.toDetailDTOList(manages);
    }

    @Override
    public boolean doesUserManageFacility(CheckManageDTO dto) {
        return managesRepo
                .findByUserIdAndFacilityIdAndIsDeletedFalse(dto.getUserId(), dto.getFacilityId())
                .isPresent();
    }

}