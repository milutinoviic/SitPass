package com.example.sitpassbek.dto.comment;

import lombok.Data;

@Data
public class CreateCommentDTO {

    private String text;

    private Long repliesToId;
}
