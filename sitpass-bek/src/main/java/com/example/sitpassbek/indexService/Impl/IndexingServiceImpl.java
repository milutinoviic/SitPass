package com.example.sitpassbek.indexService.Impl;

import com.example.sitpassbek.exceptions.LoadingException;
import com.example.sitpassbek.exceptions.NotFoundException;
import com.example.sitpassbek.indexModel.FacilityIndex;
import com.example.sitpassbek.indexRepository.FacilityIndexRepository;
import com.example.sitpassbek.indexService.FileService;
import com.example.sitpassbek.indexService.IndexingService;
import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class IndexingServiceImpl implements IndexingService {


    private final FacilityIndexRepository facilityIndexRepository;

    private final FacilityRepository facilityRepository;

    private final FileService fileService;

    private final LanguageDetector languageDetector;


    @Override
    @Transactional
    public String indexDocument(MultipartFile documentFile,Long facilityId) {
        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new NotFoundException("Facility not found: " + facilityId));

        String serverFilename = null;
        String content = null;
        String lang = null;

        // čuvanje PDF fajla i parsiranje sadržaja
        if (documentFile != null && !documentFile.isEmpty()) {
            serverFilename = fileService.store(documentFile, UUID.randomUUID().toString());
            content = extractDocumentContent(documentFile);
            lang = detectLanguage(content);
        }

        // kreiranje ES index objekta
        FacilityIndex idx = new FacilityIndex();
        idx.setId(facility.getId().toString());
        idx.setName(facility.getName());

        // opis iz baze
        String descLang = detectLanguage(facility.getDescription());
        if ("SR".equalsIgnoreCase(descLang)) {
            idx.setDescriptionSrp(facility.getDescription());
        } else {
            idx.setDescriptionEng(facility.getDescription());
        }

        // opis iz PDF fajla (ako postoji)
        if (content != null) {
            if ("SR".equalsIgnoreCase(lang)) {
                idx.setFileDescriptionSrp(content);
            } else {
                idx.setFileDescriptionEng(content);
            }
            idx.setServerFilename(serverFilename);
        }

        // snimanje u Elasticsearch
        facilityIndexRepository.save(idx);

        return serverFilename;
    }

    private String extractDocumentContent(MultipartFile multipartPdfFile) {
        try (var pdfFile = multipartPdfFile.getInputStream()) {
            PDDocument pdDocument = PDDocument.load(pdfFile);
            PDFTextStripper textStripper = new PDFTextStripper();
            String content = textStripper.getText(pdDocument);
            pdDocument.close();
            return content;
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing PDF.", e);
        }
    }

    private String detectLanguage(String text) {
        var detectedLanguage = languageDetector.detect(text).getLanguage().toUpperCase();
        if ("HR".equals(detectedLanguage)) {
            detectedLanguage = "SR";
        }
        return detectedLanguage;
    }
}
