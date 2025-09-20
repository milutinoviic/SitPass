package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByIdAndIsDeletedFalse(Long id);

    List<Exercise> findByUserIdAndIsDeletedFalse(Long userId);

    @Query("SELECT COUNT(e) FROM Exercise e " +
            "WHERE e.user.id = :userId " +
            "AND e.facility.id = :facilityId " +
            "AND e.isDeleted = false " +
            "AND e.until < :now")
    Long countPastVisits(
            @Param("userId") Long userId,
            @Param("facilityId") Long facilityId,
            @Param("now") LocalDateTime now
    );

}
