package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.facility.FacilityAvgRatingDTO;
import com.example.sitpassbek.indexRepository.FacilityIndexRepository;
import com.example.sitpassbek.indexmodel.FacilityIndex;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.service.FileService;
import com.example.sitpassbek.service.IndexService;
import com.example.sitpassbek.service.ReviewService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class IndexServiceImpl implements IndexService {

    private final FacilityIndexRepository indexRepo;
    private final FacilityRepository facilityRepo;
    private final FileService fileService;
    private final LanguageDetector languageDetector;
    private final ReviewService reviewService;

    @Autowired
    public IndexServiceImpl(FacilityIndexRepository facilityIndexRepository, FacilityRepository facilityRepository, FileService fileService, ReviewService reviewService,LanguageDetector languageDetector) {
        this.indexRepo = facilityIndexRepository;
        this.facilityRepo = facilityRepository;
        this.fileService = fileService;
        this.reviewService = reviewService;
        this.languageDetector = languageDetector;

    }

    @Override
    @Transactional
    public String indexDocument(Long facilityId, MultipartFile documentFile) {

        Facility facility = facilityRepo.findById(facilityId)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found: " + facilityId));

        String serverFilename = null;
        String content = null;
        String lang = null;

        // 1) Store file
        if (documentFile != null && !documentFile.isEmpty()) {
            // Store file
            serverFilename = fileService.store(documentFile, UUID.randomUUID().toString());

            // Extract content
            content = extractDocumentContent(documentFile);
            lang = detectLanguage(content);
        }

        FacilityIndex idx = buildFacilityIndex(facility);


        if (content != null) {
            if ("SR".equalsIgnoreCase(lang)) {
                idx.setFileDescriptionSr(content);
            } else {
                idx.setFileDescriptionEn(content);
            }
            idx.setServerFilename(serverFilename);
        }

        indexRepo.save(idx);

        return serverFilename;
    }

    private FacilityIndex buildFacilityIndex(Facility facility) {

        FacilityIndex idx = new FacilityIndex();

        idx.setId(facility.getId().toString());
        idx.setName(facility.getName());

        String lang = detectLanguage(facility.getDescription());
        if ("SR".equalsIgnoreCase(lang)) {
            idx.setDescriptionSr(facility.getDescription());
        } else {
            idx.setDescriptionEn(facility.getDescription());
        }

        idx.setReviewCount(reviewService.getCountOfReviewsForFacility(facility.getId()));

        FacilityAvgRatingDTO dto = reviewService.getFacilityAvgRating(facility.getId());
        if (dto != null) {
            idx.setAvgEquipmentGrade(dto.getAvgEquipment());
            idx.setAvgStaffGrade(dto.getAvgStaff());
            idx.setAvgHygieneGrade(dto.getAvgHygene());
            idx.setAvgSpaceGrade(dto.getAvgSpace());
        } else {

            idx.setAvgEquipmentGrade(0.0);
            idx.setAvgStaffGrade(0.0);
            idx.setAvgHygieneGrade(0.0);
            idx.setAvgSpaceGrade(0.0);
        }

        return idx;
    }


    private String extractDocumentContent(MultipartFile multipartPdfFile) {

        try (var pdfFile = multipartPdfFile.getInputStream()) {
            PDDocument pdDocument = PDDocument.load(pdfFile);
            PDFTextStripper textStripper = new PDFTextStripper();
            String content = textStripper.getText(pdDocument);
            pdDocument.close();
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Error while trying to load PDF file content.", e);
        }
    }

    private String detectLanguage(String text) {

        var detectedLanguage = languageDetector.detect(text).getLanguage().toUpperCase();
        if ("HR".equals(detectedLanguage)) {
            detectedLanguage = "SR";
        }
        return detectedLanguage;
    }

    @Override
    public void updateRatingsInFacility(Facility facility) {

        FacilityAvgRatingDTO dto = reviewService.getFacilityAvgRating(facility.getId());
        Optional<FacilityIndex> idx = indexRepo.findById(facility.getId().toString());

        if (dto != null && idx.isPresent()) {
            FacilityIndex index = idx.get();
            index.setReviewCount(idx.get().getReviewCount()+1);
            index.setAvgEquipmentGrade(dto.getAvgEquipment());
            index.setAvgStaffGrade(dto.getAvgStaff());
            index.setAvgHygieneGrade(dto.getAvgHygene());
            index.setAvgSpaceGrade(dto.getAvgSpace());
            indexRepo.save(index);
        }

    }

}
