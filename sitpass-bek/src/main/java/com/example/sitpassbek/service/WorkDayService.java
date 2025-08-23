package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.workdayDTO.WorkDayDTO;

import java.util.List;

public interface WorkDayService {

    List<WorkDayDTO> getCompleteScheduleForCurrentWeek(Long facilityId);
}
