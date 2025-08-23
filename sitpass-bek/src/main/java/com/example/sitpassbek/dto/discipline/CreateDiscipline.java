package com.example.sitpassbek.dto.discipline;

import com.example.sitpassbek.model.Facility;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateDiscipline {

    @NotNull(message = "name cannot be null")
    @Size(min = 1, message = "name must be at least 1 characters")
    private String name;

}
