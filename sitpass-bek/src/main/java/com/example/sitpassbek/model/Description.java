package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverFilename;

    @ManyToOne
    private Facility facility;

    @ManyToOne
    private User user;

    private boolean isDeleted;
}