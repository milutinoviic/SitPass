package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Manages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isDeleted;

    @ManyToOne
    private Facility facility;


}