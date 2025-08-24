package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.workday.CreateWorkDayDTO;
import com.example.sitpassbek.dto.workday.WorkDayDTO;
import com.example.sitpassbek.service.WorkDayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWorkDay(@PathVariable Long id) {
        workDayService.deleteWorkDay(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{facilityId}")
    public ResponseEntity<WorkDayDTO> createWorkDay(
            @PathVariable Long facilityId,
            @Valid @RequestBody CreateWorkDayDTO dto) {

        WorkDayDTO workDayDTO = workDayService.createWorkDay(facilityId, dto);
        return ResponseEntity.ok(workDayDTO);
    }


}
