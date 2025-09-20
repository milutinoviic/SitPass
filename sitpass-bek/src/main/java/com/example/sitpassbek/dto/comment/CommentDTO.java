package com.example.sitpassbek.dto.comment;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentDTO {

    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private Long userId;
    private List<CommentDTO> replies;

}
