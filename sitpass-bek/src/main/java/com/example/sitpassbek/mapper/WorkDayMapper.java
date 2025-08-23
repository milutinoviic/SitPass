package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.workday.WorkDayDTO;
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
}
