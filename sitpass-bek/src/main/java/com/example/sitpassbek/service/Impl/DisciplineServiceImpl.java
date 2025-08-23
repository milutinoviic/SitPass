package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.discipline.CreateDiscipline;
import com.example.sitpassbek.dto.discipline.DisciplineDTO;
import com.example.sitpassbek.mapper.DisciplineMapper;
import com.example.sitpassbek.model.Discipline;
import com.example.sitpassbek.repository.DisciplineRepository;
import com.example.sitpassbek.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;

    @Autowired
    public DisciplineServiceImpl(DisciplineRepository disciplineRepository, DisciplineMapper disciplineMapper) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
    }

    @Override
    public List<DisciplineDTO> getDisciplineList() {

        List<Discipline> disciplineList = disciplineRepository.findAllByIsDeletedFalse();

        return disciplineMapper.toDTOList(disciplineList);
    }

    @Override
    public DisciplineDTO createDiscipline(CreateDiscipline createDiscipline) {

        Discipline existing = disciplineRepository.findByNameAndIsDeletedFalse(createDiscipline.getName());

        if (existing != null) {
            throw new RuntimeException("Discipline with this name already exists");
        }

        Discipline discipline = new Discipline();
        discipline.setName(createDiscipline.getName());
        discipline.setDeleted(false);

        disciplineRepository.save(discipline);
        return disciplineMapper.toDTO(discipline);

    }

    @Override
    public void deleteDiscipline(Long disciplineId) {

        Discipline discipline = disciplineRepository.findByIdAndIsDeletedFalse(disciplineId);

        if (discipline == null) {
            throw new RuntimeException("Discipline not found");
        }

        discipline.setDeleted(true);
        disciplineRepository.save(discipline);

    }



}
