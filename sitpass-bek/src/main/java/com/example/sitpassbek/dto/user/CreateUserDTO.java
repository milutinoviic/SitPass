package com.example.sitpassbek.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotNull(message = "email cannot be null")
    @Email(message = "email must be valid")
    private String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    @NotNull(message = "address cannot be null")
    @Size(min = 3, message = "address must be at least 3 characters")
    private String address;

}
