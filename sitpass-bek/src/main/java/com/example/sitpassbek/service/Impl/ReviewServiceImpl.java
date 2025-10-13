package com.example.sitpassbek.service.Impl;


import com.example.sitpassbek.dto.facility.FacilityAvgRatingDTO;
import com.example.sitpassbek.dto.review.CreateReviewDTO;
import com.example.sitpassbek.dto.review.ReviewDTO;
import com.example.sitpassbek.mapper.ReviewMapper;
import com.example.sitpassbek.model.*;
import com.example.sitpassbek.repository.*;
import com.example.sitpassbek.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final UserRepository userRepository;
    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private RateRepository rateRepository;
    private CommentRepository commentRepository;
    private FacilityRepository facilityRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, RateRepository rateRepository, CommentRepository commentRepository, FacilityRepository facilityRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.rateRepository = rateRepository;
        this.commentRepository = commentRepository;
        this.facilityRepository = facilityRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ReviewDTO createReviewWithRateAndOptionalComment(Long facilityId,CreateReviewDTO createReviewDTO) {

        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + facilityId));

        User user = userRepository.findByIdAndIsDeletedFalse(createReviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id "+ createReviewDTO.getUserId()));


        Rate rate = new Rate();
        rate.setEquipment(createReviewDTO.getRate().getEquipment());
        rate.setStaff(createReviewDTO.getRate().getStaff());
        rate.setHygiene(createReviewDTO.getRate().getHygiene());
        rate.setSpace(createReviewDTO.getRate().getSpace());
        rate.setDeleted(false);
        Rate savedRate = rateRepository.save(rate);

        Review review = new Review();
        review.setCreatedAt(LocalDateTime.now());
        review.setHidden(false);
        review.setDeleted(false);
        review.setRate(savedRate);
        review.setFacility(facility);
        review.setUser(user);
        Review savedReview = reviewRepository.save(review);

        savedRate.setReview(savedReview);
        rateRepository.save(savedRate);

        Comment savedComment = null;
        if (createReviewDTO.getComment() != null) {
            Comment comment = new Comment();
            comment.setText(createReviewDTO.getComment().getText());
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUser(user);
            comment.setDeleted(false);

            comment.setReview(savedReview);

            savedComment = commentRepository.save(comment);
        }
        recalculateTotalRating(review.getFacility());

        return reviewMapper.toDto(savedReview);
    }

    @Override
    public void deleteReview(Long reviewId) {

        Review review = reviewRepository.findByIdAndIsDeletedFalse(reviewId);

        if (review == null) {
            throw new RuntimeException("Review not found with id " + reviewId);
        }

        Rate rate = review.getRate();
        if (rate != null) {
            rate.setDeleted(true);
            rateRepository.save(rate);
        }

        Comment comment = review.getComment();
        if (comment != null) {
            comment.setDeleted(true);
            commentRepository.save(comment);
        }

        review.setDeleted(true);
        reviewRepository.save(review);

        recalculateTotalRating(review.getFacility());

    }

    @Override
    @Transactional
    public List<ReviewDTO> getAllReviewsForFacility(Long facilityId) {
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + facilityId));

        List<Review> reviewList = reviewRepository.findAllByFacilityAndIsDeletedFalse(facility);

        return reviewMapper.toDtoList(reviewList);
    }

    private void recalculateTotalRating(Facility facility) {
        if (facility == null) return;

        List<Review> activeReviews = reviewRepository.findAllByFacilityAndIsDeletedFalse(facility);

        double total = 0;
        int count = 0;

        for (Review r : activeReviews) {
            Rate rate = r.getRate();
            if (rate != null && !rate.isDeleted()) {
                double avg = (rate.getEquipment() + rate.getStaff() + rate.getHygiene() + rate.getSpace()) / 4.0;
                total += avg;
                count++;
            }
        }

        facility.setTotalRating(count > 0 ? total / count : 0.0);
        facilityRepository.save(facility);
    }

    @Override
    public int getCountOfReviewsForFacility(Long facility) {
        return reviewRepository.countByFacilityId(facility);
    }

    @Override
    public FacilityAvgRatingDTO getFacilityAvgRating(Long facilityId) {
        // Dohvati objekat
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + facilityId));

        List<Review> activeReviews = facility.getReviews().stream()
                .filter(r -> r.getRate() != null && !r.getRate().isDeleted() && !r.isDeleted())
                .toList();

        if (activeReviews.isEmpty()) {
            FacilityAvgRatingDTO dto = new FacilityAvgRatingDTO();
            dto.setFacilityId(facility.getId());
            dto.setAvgEquipment(null);
            dto.setAvgHygene(null);
            dto.setAvgSpace(null);
            dto.setAvgStaff(null);
            return dto;
        }

        double avgEquipment = activeReviews.stream()
                .mapToInt(r -> r.getRate().getEquipment())
                .average().orElse(0.0);

        double avgHygene = activeReviews.stream()
                .mapToInt(r -> r.getRate().getHygiene())
                .average().orElse(0.0);

        double avgSpace = activeReviews.stream()
                .mapToInt(r -> r.getRate().getSpace())
                .average().orElse(0.0);

        double avgStaff = activeReviews.stream()
                .mapToInt(r -> r.getRate().getStaff())
                .average().orElse(0.0);

        FacilityAvgRatingDTO dto = new FacilityAvgRatingDTO();
        dto.setFacilityId(facility.getId());
        dto.setAvgEquipment(avgEquipment);
        dto.setAvgHygene(avgHygene);
        dto.setAvgSpace(avgSpace);
        dto.setAvgStaff(avgStaff);

        return dto;
    }








}
