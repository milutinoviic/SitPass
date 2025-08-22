package com.example.sitpassbek.model;

import com.example.sitpassbek.enums.DayOfWeekEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validFrom;

    @Enumerated(EnumType.STRING)
    private DayOfWeekEnum day;

    @Column(name = "start_time")
    private LocalDateTime from;

    @Column(name = "end_time")
    private LocalDateTime until;

    private boolean isDeleted;

    @ManyToOne
    private Facility facility;
}