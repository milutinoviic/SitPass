package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.index.RangeDTO;
import com.example.sitpassbek.indexmodel.FacilityIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SearchService {

    Page<FacilityIndex> simpleSearch(List<String> keywords,
                                     Map<String, RangeDTO> ranges, boolean isAsc,
                                     Pageable pageable);

    Page<FacilityIndex> advancedSearch(List<String> tokens, Map<String, RangeDTO> ranges,  boolean isAsc, Pageable pageable);
    Page<FacilityIndex> MLTSearch(String expression, boolean isAsc, Pageable pageable);
}
