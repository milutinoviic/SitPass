package com.example.sitpassbek.indexRepository;

import com.example.sitpassbek.indexmodel.FacilityIndex;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityIndexRepository extends JpaRepository<FacilityIndex, Long> {

    @NotNull
    Optional<FacilityIndex> findById(@NotNull String id);

}
