package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="role",discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String password;

    private boolean isDeleted;

}