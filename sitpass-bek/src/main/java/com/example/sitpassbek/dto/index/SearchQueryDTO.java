package com.example.sitpassbek.dto.index;

import java.util.List;
import java.util.Map;

public record SearchQueryDTO(
        List<String> keywords,                // for simple search
        List<String> expression,              // for advanced search
        Map<String, RangeDTO> ranges, // map from numeric field name -> min/max
        boolean isAsc
) {}
