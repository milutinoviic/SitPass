package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.discipline.CreateDiscipline;
import com.example.sitpassbek.dto.discipline.DisciplineDTO;
import com.example.sitpassbek.dto.disciplineFacility.DisciplineToFromFacility;
import com.example.sitpassbek.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/disciplineList")
    public List<DisciplineDTO> getDisciplineList() {
        return disciplineService.getDisciplineList();
    }

    @PostMapping("/createDiscipline")
    public DisciplineDTO createDiscipline(@Valid @RequestBody CreateDiscipline createDiscipline) {
        return disciplineService.createDiscipline(createDiscipline);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
    }

    @PostMapping("/addDisciplineListToFacility")
    public ResponseEntity<Void> addDisciplineListToFacility(@RequestBody DisciplineToFromFacility disciplineToFromFacility) {
        disciplineService.addDisciplineListToFacility(disciplineToFromFacility);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteDisciplinesFromFacility")
    public ResponseEntity<Void> removeDisciplineListFromFacility(
            @RequestBody DisciplineToFromFacility request) {
        disciplineService.removeDisciplineListFromFacility(request);
        return ResponseEntity.ok().build();
    }

}
