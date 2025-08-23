package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.discipline.DisciplineDTO;
import com.example.sitpassbek.model.Discipline;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DisciplineMapper {

    public static DisciplineDTO toDTO(Discipline discipline) {
        return new DisciplineDTO(
                discipline.getId(),
                discipline.getName()
        );
    }

    public static List<DisciplineDTO> toDTOList(List<Discipline> disciplines) {
        return disciplines.stream()
                .map(DisciplineMapper::toDTO)
                .collect(Collectors.toList());
    }
}
