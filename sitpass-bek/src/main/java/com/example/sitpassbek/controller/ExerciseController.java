package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.exercise.CreateExerciseDTO;
import com.example.sitpassbek.dto.exercise.ExerciseDTO;
import com.example.sitpassbek.service.ExerciseService;
import com.example.sitpassbek.service.Impl.ExerciseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseServiceImpl exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<ExerciseDTO> createExercise(@Valid @RequestBody CreateExerciseDTO dto) {
        ExerciseDTO saved = exerciseService.createExercise(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExerciseDTO>> getAllExercisesByUser(@PathVariable Long userId) {
        List<ExerciseDTO> exercises = exerciseService.getAllExercisesByUser(userId);
        return ResponseEntity.ok(exercises);
    }

}
