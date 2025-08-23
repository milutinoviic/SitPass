package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.discipline.CreateDiscipline;
import com.example.sitpassbek.dto.discipline.DisciplineDTO;

import java.util.List;

public interface DisciplineService {

    List<DisciplineDTO> getDisciplineList();

    DisciplineDTO createDiscipline(CreateDiscipline createDiscipline);

    void deleteDiscipline(Long disciplineId);

}
