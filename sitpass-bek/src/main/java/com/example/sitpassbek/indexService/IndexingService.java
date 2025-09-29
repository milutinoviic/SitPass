package com.example.sitpassbek.indexService;

import org.springframework.web.multipart.MultipartFile;

public interface IndexingService {

    String indexDocument(MultipartFile documentFile,Long facilityId);
}
