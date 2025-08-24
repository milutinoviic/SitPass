package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.exercise.CreateExerciseDTO;
import com.example.sitpassbek.dto.exercise.ExerciseDTO;
import com.example.sitpassbek.dto.workday.WorkDayDTO;
import com.example.sitpassbek.mapper.ExerciseMapper;
import com.example.sitpassbek.mapper.WorkDayMapper;
import com.example.sitpassbek.model.Exercise;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.model.WorkDay;
import com.example.sitpassbek.repository.ExerciseRepository;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.repository.UserRepository;
import com.example.sitpassbek.service.ExerciseService;
import com.example.sitpassbek.service.WorkDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {


    private final ExerciseRepository exerciseRepository;
    private final FacilityRepository facilityRepository;
    private final WorkDayService workDayService;
    private final WorkDayMapper workDayMapper;
    private final ExerciseMapper exerciseMapper;
    private final UserRepository userRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, FacilityRepository facilityRepository, WorkDayService workDayService, WorkDayMapper workDayMapper, ExerciseMapper exerciseMapper, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.facilityRepository = facilityRepository;
        this.workDayService = workDayService;
        this.workDayMapper = workDayMapper;
        this.exerciseMapper = exerciseMapper;
        this.userRepository = userRepository;
    }



    @Transactional
    @Override
    public ExerciseDTO createExercise(CreateExerciseDTO dto) {

        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(dto.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));

        User user = userRepository.findByIdAndIsDeletedFalse(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate exerciseDate = dto.getFrom().toLocalDate();

        LocalDateTime now = LocalDateTime.now();


        if (dto.getFrom().isBefore(now)) {
            throw new RuntimeException("Exercise cannot be scheduled in the past");
        }

        List<WorkDayDTO> weeklyScheduleDto = workDayService.getCompleteScheduleForCurrentWeek(dto.getFacilityId());
        List<WorkDay> weeklySchedule = workDayMapper.toEntityList(weeklyScheduleDto,facility);

        DayOfWeek exerciseDay = exerciseDate.getDayOfWeek();
        WorkDay workDay = weeklySchedule.stream()
                .filter(wd -> wd.getDay().name().equalsIgnoreCase(exerciseDay.name()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Facility not working that day"));

        if (dto.getFrom().toLocalTime().isBefore(workDay.getFrom().toLocalTime()) ||
                dto.getUntil().toLocalTime().isAfter(workDay.getUntil().toLocalTime())) {
            throw new RuntimeException("Exercise not within working hours");
        }

        long minutes = Duration.between(dto.getFrom(), dto.getUntil()).toMinutes();
        if (!(minutes == 60 || minutes == 90)) {
            throw new RuntimeException("Duration must be exactly 1h or 1.5h");
        }

        int startMinute = dto.getFrom().getMinute();
        if (!(startMinute == 0 || startMinute == 30)) {
            throw new RuntimeException("Start time must be on the hour or half past");
        }


        Exercise exercise = new Exercise();
        exercise.setFrom(dto.getFrom());
        exercise.setDeleted(false);
        exercise.setUser(user);
        exercise.setUntil(dto.getUntil());
        exercise.setFacility(facility);

        Exercise saved = exerciseRepository.save(exercise);
        return exerciseMapper.toDTO(saved);

    }

    @Transactional
    @Override
    public void deleteExercise(Long exerciseId) {
        Exercise exercise = exerciseRepository.findByIdAndIsDeletedFalse(exerciseId)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime exerciseStart = exercise.getFrom();

        if (!now.isBefore(exerciseStart.minusDays(1))) {
            throw new RuntimeException("Cannot cancel exercise less than 1 day before start");
        }

        exercise.setDeleted(true);
        exerciseRepository.save(exercise);
    }

    @Override
    public List<ExerciseDTO> getAllExercisesByUser(Long userId) {
        List<Exercise> exercises = exerciseRepository.findByUserIdAndIsDeletedFalse(userId);
        return ExerciseMapper.toDTOList(exercises);

    }






}
