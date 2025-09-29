package com.example.sitpassbek.indexRepository;

import com.example.sitpassbek.indexModel.FacilityIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityIndexRepository extends ElasticsearchRepository<FacilityIndex, String> {

}
