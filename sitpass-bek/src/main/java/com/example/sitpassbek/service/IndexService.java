package com.example.sitpassbek.service;

import com.example.sitpassbek.model.Facility;
import org.springframework.web.multipart.MultipartFile;

public interface IndexService {

    String indexDocument(Long facilityId, MultipartFile documentFile);

    void updateRatingsInFacility(Facility facility);
}
