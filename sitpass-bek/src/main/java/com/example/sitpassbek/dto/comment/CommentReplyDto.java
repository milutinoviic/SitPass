package com.example.sitpassbek.dto.comment;

import lombok.Data;

@Data
public class CommentReplyDto {

    private Long commentId;

    private Long userId;

    private String content;
}
