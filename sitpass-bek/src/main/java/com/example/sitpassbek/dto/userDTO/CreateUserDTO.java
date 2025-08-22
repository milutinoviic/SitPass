package com.example.sitpassbek.dto.userDTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateUserDTO {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

}
