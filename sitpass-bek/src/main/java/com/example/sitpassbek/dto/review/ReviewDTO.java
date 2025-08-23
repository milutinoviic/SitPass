package com.example.sitpassbek.dto.review;

import com.example.sitpassbek.dto.comment.CommentDTO;
import com.example.sitpassbek.dto.rate.RateDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {

    private Long id;
    private LocalDateTime createdAt;
    private Integer exerciseCount;
    private Boolean hidden;
    private boolean deleted;
    private Long userId;
    private RateDTO rate;
    private CommentDTO comment;

}
