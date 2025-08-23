package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.disciplineFacility.DisciplineToFromFacility;
import com.example.sitpassbek.dto.discipline.CreateDiscipline;
import com.example.sitpassbek.dto.discipline.DisciplineDTO;
import com.example.sitpassbek.mapper.DisciplineMapper;
import com.example.sitpassbek.model.Discipline;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.repository.DisciplineRepository;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final DisciplineMapper disciplineMapper;
    private final FacilityRepository facilityRepository;

    @Autowired
    public DisciplineServiceImpl(DisciplineRepository disciplineRepository, DisciplineMapper disciplineMapper,FacilityRepository facilityRepository) {
        this.disciplineRepository = disciplineRepository;
        this.disciplineMapper = disciplineMapper;
        this.facilityRepository = facilityRepository;
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


    @Override
    @Transactional
    public void addDisciplineListToFacility(DisciplineToFromFacility disciplineToFromFacility) {

        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(disciplineToFromFacility.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + disciplineToFromFacility.getFacilityId()));

        List<Discipline> disciplines = disciplineRepository.findAllByIdInAndIsDeletedFalse(disciplineToFromFacility.getDisciplineIds());

        if (disciplines.size() != disciplineToFromFacility.getDisciplineIds().size()) {
            throw new RuntimeException("Some disciplines not found or are deleted");
        }

        for (Discipline d : disciplines) {

            if (!facility.getDisciplines().contains(d)) {
                facility.getDisciplines().add(d);
            }

            if (!d.getFacilities().contains(facility)) {
                d.getFacilities().add(facility);
            }
        }

        facilityRepository.save(facility);
        disciplineRepository.saveAll(disciplines);
    }

    @Override
    @Transactional
    public void removeDisciplineListFromFacility(DisciplineToFromFacility request) {

        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(request.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + request.getFacilityId()));

        List<Discipline> disciplines = disciplineRepository.findAllByIdInAndIsDeletedFalse(request.getDisciplineIds());

        if (disciplines.size() != request.getDisciplineIds().size()) {
            throw new RuntimeException("Some disciplines not found or are deleted");
        }

        List<Discipline> current = facility.getDisciplines();
        if (current == null || current.isEmpty()) {
            throw new RuntimeException("Facility has no disciplines assigned");
        }

        current.removeAll(disciplines);

        facility.setDisciplines(current);
        facilityRepository.save(facility);
    }








}
