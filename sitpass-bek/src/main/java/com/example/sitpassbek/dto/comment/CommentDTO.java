package com.example.sitpassbek.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private Long userId;
    private Long repliesToId;

}
