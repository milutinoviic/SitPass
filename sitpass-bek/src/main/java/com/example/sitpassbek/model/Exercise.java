package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime from;

    @Column(name = "end_time")
    private LocalDateTime until;

    private boolean isDeleted;

    @ManyToOne
    private User user;

    @ManyToOne
    private Facility facility;
}
