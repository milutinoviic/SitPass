package com.example.sitpassbek.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String surname;

    private LocalDate createdAt;

    private LocalDate birthday;

    private String phoneNumber;

    private String address;

    private String city;

    private String zipCode;

    private String email;

}
