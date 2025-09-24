package com.example.sitpassbek.indexRepository;

import com.example.sitpassbek.indexModel.FacilityIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FacilityIndexRepository extends ElasticsearchRepository<FacilityIndex, String> {

}
