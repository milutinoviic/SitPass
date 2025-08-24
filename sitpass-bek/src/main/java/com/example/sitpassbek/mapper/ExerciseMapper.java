package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.exercise.ExerciseDTO;
import com.example.sitpassbek.model.Exercise;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExerciseMapper {

    public static ExerciseDTO toDTO(Exercise exercise) {
        return new ExerciseDTO(
                exercise.getId(),
                exercise.getUser().getId(),
                exercise.getFacility().getId(),
                exercise.getFrom(),
                exercise.getUntil()
        );
    }

    public static List<ExerciseDTO> toDTOList(List<Exercise> exercises) {
        return exercises.stream()
                .map(ExerciseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
