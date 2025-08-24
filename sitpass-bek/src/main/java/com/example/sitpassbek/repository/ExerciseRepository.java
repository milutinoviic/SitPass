package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByIdAndIsDeletedFalse(Long id);

    List<Exercise> findByUserIdAndIsDeletedFalse(Long userId);

}
