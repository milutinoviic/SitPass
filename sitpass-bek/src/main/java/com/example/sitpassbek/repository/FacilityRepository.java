package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    Optional<Facility> findByIdAndIsDeletedFalse(Long id);

}
