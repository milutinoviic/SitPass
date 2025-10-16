package com.example.sitpassbek.service;

import com.example.sitpassbek.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image uploadImage(Long facilityId, MultipartFile file);

    List<Image> getImagesForFacility(Long facilityId);

    void deleteImage(Long imageId);
}
