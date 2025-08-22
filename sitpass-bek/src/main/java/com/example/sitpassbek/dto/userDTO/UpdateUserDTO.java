package com.example.sitpassbek.dto.userDTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserDTO {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String email;

}
