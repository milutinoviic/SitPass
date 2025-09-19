package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    Optional<Facility> findByIdAndIsDeletedFalse(Long id);

}
