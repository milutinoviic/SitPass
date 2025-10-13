package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.model.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Long> {

    Review findByIdAndIsDeletedFalse(Long id);

    List<Review> findAllByFacilityAndIsDeletedFalse(Facility facility);

    int countByFacilityId(Long facilityId);




}
