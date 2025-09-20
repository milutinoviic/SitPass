package com.example.sitpassbek.mapper;

import com.example.sitpassbek.dto.comment.CommentDTO;
import com.example.sitpassbek.dto.rate.RateDTO;
import com.example.sitpassbek.dto.review.ReviewDTO;
import com.example.sitpassbek.model.Comment;
import com.example.sitpassbek.model.Rate;
import com.example.sitpassbek.model.Review;
import com.example.sitpassbek.repository.CommentRepository;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewMapper {

    public ReviewDTO toDto(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setExerciseCount(review.getExerciseCount());
        dto.setHidden(review.getHidden());
        dto.setDeleted(review.isDeleted());
        dto.setUserId(review.getUser() != null ? review.getUser().getId() : null);

        if (review.getRate() != null) {
            dto.setRate(toRateDto(review.getRate()));
        }

        if (review.getComment() != null) {
            dto.setComment(toCommentDto(review.getComment()));
        }

        return dto;
    }

    public List<ReviewDTO> toDtoList(List<Review> reviews) {
        List<ReviewDTO> dtoList = new ArrayList<>();
        for (Review review : reviews) {
            dtoList.add(toDto(review));
        }
        return dtoList;
    }

    private RateDTO toRateDto(Rate rate) {
        RateDTO dto = new RateDTO();
        dto.setId(rate.getId());
        dto.setEquipment(rate.getEquipment());
        dto.setStaff(rate.getStaff());
        dto.setHygiene(rate.getHygiene());
        dto.setSpace(rate.getSpace());
        return dto;
    }

    public CommentDTO toCommentDto(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUserId(comment.getUser() != null ? comment.getUser().getId() : null);

        List<CommentDTO> repliesDto = new ArrayList<>();
        if (comment.getReplies() != null) {
            for (Comment reply : comment.getReplies()) {
                repliesDto.add(toCommentDto(reply));
            }
        }
        dto.setReplies(repliesDto);

        return dto;
    }

}
