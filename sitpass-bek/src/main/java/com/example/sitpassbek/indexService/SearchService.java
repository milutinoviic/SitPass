package com.example.sitpassbek.indexService;

import com.example.sitpassbek.indexModel.FacilityIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {

    Page<FacilityIndex> simpleSearch(List<String> keywords, Pageable pageable, boolean isKNN);

    Page<FacilityIndex> advancedSearch(List<String> expression, Pageable pageable);

}
