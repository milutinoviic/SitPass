package com.example.sitpassbek.dto.review;

import com.example.sitpassbek.dto.comment.CreateCommentDTO;
import com.example.sitpassbek.dto.rate.CreateRateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDTO {

    private CreateRateDTO rate;

    private CreateCommentDTO comment;

    private Long userId;

}
