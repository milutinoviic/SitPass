package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.index.FacilityDocumentFile;
import com.example.sitpassbek.dto.index.FacilityDocumentFileDTO;
import com.example.sitpassbek.indexRepository.FacilityIndexRepository;
import com.example.sitpassbek.indexmodel.FacilityIndex;
import com.example.sitpassbek.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/index")
public class IndexController {

    private final IndexService indexService;
    private final FacilityIndexRepository indexRepo;

    @Autowired
    public IndexController(IndexService indexService, FacilityIndexRepository indexRepo) {
        this.indexService = indexService;
        this.indexRepo = indexRepo;
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FacilityDocumentFile addDocumentFile(@PathVariable Long id, @ModelAttribute FacilityDocumentFileDTO documentFile) {

        MultipartFile file = documentFile != null ? documentFile.file() : null;
        String serverFilename = indexService.indexDocument(id, file);
        return new FacilityDocumentFile(serverFilename);
    }

    @GetMapping("/{id}")
    public FacilityDocumentFile getDocumentFile(@PathVariable String id) {
        FacilityIndex facilityIndex = indexRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found in index"));

        return new FacilityDocumentFile(facilityIndex.getServerFilename());
    }

}
