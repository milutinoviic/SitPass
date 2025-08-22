package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer equipment;

    private Integer staff;

    private Integer hygiene;

    private Integer space;

    private boolean isDeleted;

    @OneToOne
    private Review review;
}
