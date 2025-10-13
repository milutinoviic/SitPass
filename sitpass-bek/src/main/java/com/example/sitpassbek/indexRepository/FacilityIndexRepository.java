package com.example.sitpassbek.indexRepository;

import com.example.sitpassbek.indexmodel.FacilityIndex;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityIndexRepository extends ElasticsearchRepository<FacilityIndex, Long> {

    @NotNull
    Optional<FacilityIndex> findById(@NotNull String id);

}
