package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.discipline.CreateDiscipline;
import com.example.sitpassbek.dto.discipline.DisciplineDTO;
import com.example.sitpassbek.model.Discipline;
import com.example.sitpassbek.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
}
