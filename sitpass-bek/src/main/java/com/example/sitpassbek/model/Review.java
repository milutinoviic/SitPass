package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private Integer exerciseCount;

    private Boolean hidden;

    private boolean isDeleted;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Rate rate;
}