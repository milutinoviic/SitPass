package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.comment.CreateCommentDTO;
import com.example.sitpassbek.dto.facility.FacilityAvgRatingDTO;
import com.example.sitpassbek.dto.rate.CreateRateDTO;
import com.example.sitpassbek.dto.review.CreateReviewDTO;
import com.example.sitpassbek.dto.review.ReviewDTO;

import java.util.List;

public interface ReviewService {

    ReviewDTO createReviewWithRateAndOptionalComment(Long facilityId,CreateReviewDTO createReviewDTO);

    void deleteReview(Long reviewId);

    List<ReviewDTO> getAllReviewsForFacility(Long facilityId);

    FacilityAvgRatingDTO getFacilityAvgRating(Long facilityId);

    int getCountOfReviewsForFacility(Long facility);

}
