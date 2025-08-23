package com.example.sitpassbek.repository;

import com.example.sitpassbek.enums.DayOfWeekEnum;
import com.example.sitpassbek.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sitpassbek.model.Facility;
import org.springframework.stereotype.Repository;
import com.example.sitpassbek.model.WorkDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    Optional<WorkDay> findByFacilityAndValidFromAndDayAndIsDeletedFalse(
            Facility facility, LocalDate validFrom, DayOfWeekEnum day);

    Optional<WorkDay> findTopByFacilityAndDayAndValidFromLessThanEqualAndIsDeletedFalseOrderByValidFromDesc(
            Facility facility, DayOfWeekEnum day, LocalDate date);

    Optional<WorkDay> findByIdAndIsDeletedFalse(Long id);

}

