package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

    List<Discipline> findAllByIsDeletedFalse();

    Discipline findByNameAndIsDeletedFalse(String name);

    Discipline findByIdAndIsDeletedFalse(Long id);

    List<Discipline> findAllByIdInAndIsDeletedFalse(List<Long> ids);

    List<Discipline> findByFacilitiesIdAndIsDeletedFalse(Long facilityId);

}
