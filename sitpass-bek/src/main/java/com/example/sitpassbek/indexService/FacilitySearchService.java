package com.example.sitpassbek.indexService;

import com.example.sitpassbek.indexDto.FacilitySearchDTO;
import com.example.sitpassbek.indexModel.FacilityIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacilitySearchService {

    public Page<FacilityIndex> search(FacilitySearchDTO dto, Pageable pageable);
}
