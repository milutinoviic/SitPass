package com.example.sitpassbek.dto.disciplineFacility;

import lombok.Data;

import java.util.List;

@Data
public class DisciplineToFromFacility {

    Long facilityId;

    List<Long> disciplineIds;


}
