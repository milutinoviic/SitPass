package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isDeleted;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment repliesTo;

}
