package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.exercise.ExerciseDTO;
import com.example.sitpassbek.model.Exercise;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExerciseMapper {

    public static ExerciseDTO toDTO(Exercise exercise) {
        ExerciseDTO e = new ExerciseDTO();
        e.setId(exercise.getId());
        e.setUserId(exercise.getUser() != null ? exercise.getUser().getId() : null);
        e.setFacilityId(exercise.getFacility() != null ? exercise.getFacility().getId() : null);
        e.setFrom(exercise.getFrom());
        e.setUntil(exercise.getUntil());
        return e;
    }

    public static List<ExerciseDTO> toDTOList(List<Exercise> exercises) {
        return exercises.stream()
                .map(ExerciseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
