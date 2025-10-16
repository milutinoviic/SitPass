package com.example.sitpassbek.controller;

import com.example.sitpassbek.model.Facility;
import com.example.sitpassbek.model.Image;
import com.example.sitpassbek.repository.FacilityRepository;
import com.example.sitpassbek.repository.ImageRepository;
import com.example.sitpassbek.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final FacilityRepository facilityRepository;
    private final ImageRepository imageRepository;
    private final FileService fileService;

    /**
     * Upload jedne ili više slika u MinIO za dati Facility
     */
    @PostMapping("/{facilityId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Image> uploadImages(
            @PathVariable Long facilityId,
            @RequestParam("files") List<MultipartFile> files) {

        Facility facility = facilityRepository.findById(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found with id: " + facilityId));

        return files.stream().map(file -> {
            // 1️⃣ Snimi fajl u MinIO
            String storedFileName = fileService.store(file, UUID.randomUUID().toString());

            // 2️⃣ Upisi podatke u bazu
            Image image = new Image();
            image.setFacility(facility);
            image.setServerFilename(storedFileName);
            image.setDeleted(false);
            return imageRepository.save(image);
        }).toList();
    }

    @GetMapping("/{facilityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> getAllFacilityImages(@PathVariable Long facilityId) {
        var images = imageRepository.findByFacilityIdAndIsDeletedFalse(facilityId);

        return images.stream().map(image -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", image.getId());
            map.put("filename", image.getServerFilename());

            try {
                // Dobavi bajtove slike iz MinIO
                byte[] fileBytes = fileService.downloadFile(image.getServerFilename());
                // Pretvori u Base64 string
                String base64 = Base64.getEncoder().encodeToString(fileBytes);
                map.put("base64", "data:image/jpeg;base64," + base64);
            } catch (Exception e) {
                map.put("error", "Error reading image: " + e.getMessage());
            }

            return map;
        }).toList();
    }

}
