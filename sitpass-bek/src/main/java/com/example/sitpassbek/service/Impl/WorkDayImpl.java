package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.workday.CreateWorkDayDTO;
import com.example.sitpassbek.dto.workday.WorkDayDTO;
import com.example.sitpassbek.enums.DayOfWeekEnum;
import com.example.sitpassbek.mapper.WorkDayMapper;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.model.WorkDay;
import com.example.sitpassbek.repository.WorkDayRepository;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkDayImpl implements WorkDayService {

    private final WorkDayRepository workDayRepository;
    private final FacilityRepository facilityRepository;
    private final WorkDayMapper workDayMapper;

    @Autowired
    public WorkDayImpl(WorkDayRepository workDayRepository, FacilityRepository facilityRepository, WorkDayMapper workDayMapper) {
        this.workDayRepository = workDayRepository;
        this.facilityRepository = facilityRepository;
        this.workDayMapper = workDayMapper;
    }

    @Override
    public void deleteWorkDay(Long workDayId) {
        WorkDay workDay = workDayRepository.findByIdAndIsDeletedFalse(workDayId)
                .orElseThrow(() -> new RuntimeException("WorkDay not found"));

        workDay.setDeleted(true);
        workDayRepository.save(workDay);
    }

    @Override
    public WorkDayDTO createWorkDay(Long facilityId, CreateWorkDayDTO dto) {

        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        if (dto.getValidFrom().isBefore(LocalDate.now())) {
            throw new RuntimeException("Cannot create WorkDay in the past");
        }

        if (workDayRepository.findByFacilityAndValidFromAndDayAndIsDeletedFalse(facility, dto.getValidFrom(), dto.getDay()).isPresent()) {
            throw new RuntimeException("WorkDay for this day already exists");
        }

        WorkDay workDay = new WorkDay();
        workDay.setFacility(facility);
        workDay.setValidFrom(dto.getValidFrom());
        workDay.setDay(dto.getDay());
        workDay.setFrom(dto.getFrom());
        workDay.setUntil(dto.getUntil());
        workDay.setDeleted(false);

        WorkDay saved = workDayRepository.save(workDay);
        return workDayMapper.toDTO(saved);
    }


    @Override
    public List<WorkDayDTO> getCompleteScheduleForCurrentWeek(Long facilityId) {
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        LocalDate monday = LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        List<WorkDay> result = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = monday.plusDays(i);
            DayOfWeekEnum dayEnum = DayOfWeekEnum.valueOf(currentDate.getDayOfWeek().name());

            Optional<WorkDay> current = workDayRepository
                    .findByFacilityAndValidFromAndDayAndIsDeletedFalse(facility, currentDate, dayEnum);

            if (current.isPresent()) {

                result.add(current.get());
            } else {

                Optional<WorkDay> lastKnown = workDayRepository
                        .findTopByFacilityAndDayAndValidFromLessThanEqualAndIsDeletedFalseOrderByValidFromDesc(
                                facility, dayEnum, currentDate
                        );

                lastKnown.ifPresent(result::add);
            }
        }

        return workDayMapper.toDTOList(result);
    }


}
