package com.example.sitpassbek.indexDto;

import org.springframework.web.multipart.MultipartFile;

public record FacilityDocumentDTO(MultipartFile file, Integer facilityDatabaseId) {}
