package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Manages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManagesRepository extends JpaRepository<Manages, Long> {

    Optional<Manages> findByIdAndIsDeletedFalse(Long id);

    Optional<Manages> findByUserIdAndFacilityIdAndIsDeletedFalse(Long userId, Long facilityId);

    List<Manages> findAllByFacilityIdAndIsDeletedFalse(Long facilityId);



}
