package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.workday.WorkDayDTO;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.model.WorkDay;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkDayMapper {

    public static WorkDayDTO toDTO(WorkDay workDay) {
        return new WorkDayDTO(
                workDay.getId(),
                workDay.getValidFrom(),
                workDay.getDay(),
                workDay.getFrom(),
                workDay.getUntil()
        );
    }

    public static List<WorkDayDTO> toDTOList(List<WorkDay> workDays) {
        return workDays.stream()
                .map(WorkDayMapper::toDTO)
                .collect(Collectors.toList());
    }


    public static WorkDay toEntity(WorkDayDTO dto, Facility facility) {
        WorkDay workDay = new WorkDay();
        workDay.setId(dto.getId());
        workDay.setValidFrom(dto.getValidFrom());
        workDay.setDay(dto.getDay());
        workDay.setFrom(dto.getFrom());
        workDay.setUntil(dto.getUntil());
        workDay.setDeleted(false);
        workDay.setFacility(facility);
        return workDay;
    }

    public static List<WorkDay> toEntityList(List<WorkDayDTO> dtos, Facility facility) {
        return dtos.stream()
                .map(dto -> toEntity(dto, facility))
                .collect(Collectors.toList());
    }
}
