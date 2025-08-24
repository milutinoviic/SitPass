package com.example.sitpassbek.service.Impl;


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
    }

    @Override
    @Transactional
    public List<ReviewDTO> getAllReviewsForFacility(Long facilityId) {
        Facility facility = facilityRepository.findByIdAndIsDeletedFalse(facilityId)
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + facilityId));

        List<Review> reviewList = reviewRepository.findAllByFacilityAndIsDeletedFalse(facility);

        return reviewMapper.toDtoList(reviewList);
    }






}
