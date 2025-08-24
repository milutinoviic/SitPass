package com.example.sitpassbek.model;

import com.example.sitpassbek.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private String address;

    private boolean isDeleted;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String rejectionReason;

}
