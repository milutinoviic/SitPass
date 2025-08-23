package com.example.sitpassbek.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserDTO {

    @NotNull(message = "name cannot be null")
    @Size(min = 3, message = "name must be at least 3 characters")
    private String name;

    @NotNull(message = "surname cannot be null")
    @Size(min = 3, message = "surname must be at least 3 characters")
    private String surname;

    @NotNull(message = "birthday cannot be null")
    @Past(message = "birthday must be in the past")
    private LocalDate birthday;

    @NotNull(message = "phoneNumber cannot be null")
    @Size(min = 6, message = "phoneNumber must be at least 6 characters")
    private String phoneNumber;

    @NotNull(message = "address cannot be null")
    @Size(min = 3, message = "address must be at least 3 characters")
    private String address;

    @NotNull(message = "city cannot be null")
    @Size(min = 2, message = "city must be at least 2 characters")
    private String city;

    @NotNull(message = "email cannot be null")
    @Email(message = "email must be valid")
    private String email;

}
