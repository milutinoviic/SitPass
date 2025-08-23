package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.workday.CreateWorkDayDTO;
import com.example.sitpassbek.dto.workday.WorkDayDTO;

import java.util.List;

public interface WorkDayService {

    List<WorkDayDTO> getCompleteScheduleForCurrentWeek(Long facilityId);

    WorkDayDTO createWorkDay(Long facilityId, CreateWorkDayDTO dto);

    void deleteWorkDay(Long workDayId);
}
