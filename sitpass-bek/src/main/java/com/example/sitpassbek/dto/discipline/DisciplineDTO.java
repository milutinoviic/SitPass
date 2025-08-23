package com.example.sitpassbek.dto.discipline;

import com.example.sitpassbek.model.Facility;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DisciplineDTO {

    private Long id;

    private String name;

}
