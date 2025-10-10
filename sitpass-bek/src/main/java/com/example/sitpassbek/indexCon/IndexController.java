package com.example.sitpassbek.indexCon;

import com.example.sitpassbek.indexDto.FacilityDocumentFileDTO;
import com.example.sitpassbek.indexDto.FacilityDocumentFileResponse;
import com.example.sitpassbek.indexModel.FacilityIndex;
import com.example.sitpassbek.indexRepository.FacilityIndexRepository;
import com.example.sitpassbek.indexService.IndexingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/index")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequiredArgsConstructor
public class IndexController {

    private final IndexingService indexingService;
    private final FacilityIndexRepository repo;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public FacilityDocumentFileResponse addDocumentFile(
            @PathVariable Long id,
            @ModelAttribute FacilityDocumentFileDTO documentFile) {

        MultipartFile file = documentFile != null ? documentFile.file() : null;
        String serverFilename = indexingService.indexDocument(id, file);
        return new FacilityDocumentFileResponse(serverFilename);
    }

    @GetMapping("/{id}")
    public FacilityDocumentFileResponse getDocumentFile(@PathVariable String id) {
        FacilityIndex facilityIndex = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found in index"));

        return new FacilityDocumentFileResponse(facilityIndex.getServerFilename());
    }

}
