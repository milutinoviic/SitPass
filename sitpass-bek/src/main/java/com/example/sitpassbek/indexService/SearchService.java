package com.example.sitpassbek.indexService;

import com.example.sitpassbek.indexModel.FacilityIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    Page<FacilityIndex> searchFacilities(String keyword, Pageable pageable);
}
