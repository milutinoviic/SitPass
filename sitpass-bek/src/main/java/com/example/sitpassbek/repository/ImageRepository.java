package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByFacilityIdAndIsDeletedFalse(Long facilityId);
}
