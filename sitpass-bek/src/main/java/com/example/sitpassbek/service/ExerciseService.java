package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.exercise.CreateExerciseDTO;
import com.example.sitpassbek.dto.exercise.ExerciseDTO;
import com.example.sitpassbek.dto.exercise.PastVisitsRequestDTO;

import java.util.List;

public interface ExerciseService {

    void deleteExercise(Long exerciseId);

    ExerciseDTO createExercise(CreateExerciseDTO dto);

    List<ExerciseDTO> getAllExercisesByUser(Long userId);

    Long getPastVisits(PastVisitsRequestDTO requestDto);

}
