package com.example.sitpassbek.service;

import com.example.sitpassbek.dto.comment.CommentReplyDto;

public interface CommentService {

    void replyToComment(CommentReplyDto commentReplyDto);
}
