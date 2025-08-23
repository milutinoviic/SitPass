package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.workdayDTO.WorkDayDTO;
import com.example.sitpassbek.model.WorkDay;
import com.example.sitpassbek.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workdays")
public class WorkDayController {

    private final WorkDayService workDayService;

    @Autowired
    public WorkDayController(WorkDayService workDayService) {
        this.workDayService = workDayService;
    }

    @GetMapping("/{facilityId}/current-week")
    public List<WorkDayDTO> getCurrentWeek(@PathVariable Long facilityId) {
        return workDayService.getCompleteScheduleForCurrentWeek(facilityId);
    }
}
