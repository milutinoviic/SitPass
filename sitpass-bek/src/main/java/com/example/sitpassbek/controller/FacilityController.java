package com.example.sitpassbek.controller;

import com.example.sitpassbek.dto.facility.CreateFacilityDTO;
import com.example.sitpassbek.dto.facility.FacilityDTO;
import com.example.sitpassbek.dto.facility.UpdateFacilityDTO;
import com.example.sitpassbek.dto.review.CreateReviewDTO;
import com.example.sitpassbek.dto.review.ReviewDTO;
import com.example.sitpassbek.mapper.FacilityMapper;
import com.example.sitpassbek.service.FacilityService;
import com.example.sitpassbek.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService facilityService;
    private final FacilityMapper facilityMapper;
    private final ReviewService reviewService;

    @Autowired
    public FacilityController(FacilityService facilityService, FacilityMapper facilityMapper, ReviewService reviewService) {
        this.facilityService = facilityService;
        this.facilityMapper = facilityMapper;
        this.reviewService = reviewService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<FacilityDTO> createFacility(@Valid @RequestBody CreateFacilityDTO dto) {
        FacilityDTO facilityDTO = facilityService.createFacility(dto);
        return ResponseEntity.ok(facilityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityDTO> updateFacility(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFacilityDTO dto) {

        FacilityDTO updatedFacility = facilityService.updateFacility(id, dto);
        return ResponseEntity.ok(updatedFacility);
    }

    @PostMapping("createReview/{facilityId}")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable Long facilityId, @RequestBody CreateReviewDTO request) {
        ReviewDTO review = reviewService.createReviewWithRateAndOptionalComment(facilityId, request);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review, rate and associated comments have been logically deleted.");
    }

    @GetMapping("/facility/{facilityId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsForFacility(@PathVariable Long facilityId) {
        List<ReviewDTO> reviews = reviewService.getAllReviewsForFacility(facilityId);
        return ResponseEntity.ok(reviews);
    }

}
