package com.example.sitpassbek.indexDto;

import java.util.List;

public record FacilitySearchDTO(
        List<String> keywords,
        String booleanOperator, // "AND" or "OR" (default AND)
        Integer reviewCountFrom,
        Integer reviewCountTo,
        Double avgEquipmentFrom,
        Double avgEquipmentTo,
        Double avgStaffFrom,
        Double avgStaffTo,
        Double avgHygieneFrom,
        Double avgHygieneTo,
        Double avgSpaceFrom,
        Double avgSpaceTo,
        String sortBy, // "name" or other fields; ascending by default
        String sortDirection, // "ASC" or "DESC"
        Boolean moreLikeThis // if true, perform MLT instead of standard search
) {}

