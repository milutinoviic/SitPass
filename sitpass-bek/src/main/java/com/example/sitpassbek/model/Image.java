package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverFilename;

    @ManyToOne
    private Facility facility;

    private boolean isDeleted;
}
