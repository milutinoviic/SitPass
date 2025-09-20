package com.example.sitpassbek.service.Impl;

import com.example.sitpassbek.dto.comment.CommentReplyDto;
import com.example.sitpassbek.model.Comment;
import com.example.sitpassbek.model.User;
import com.example.sitpassbek.repository.CommentRepository;
import com.example.sitpassbek.repository.UserRepository;
import com.example.sitpassbek.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void replyToComment(CommentReplyDto commentReplyDto) {

        User user = userRepository.findUserById(commentReplyDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User nije pronađen");
        }

        Comment commentToReply = commentRepository.findById(commentReplyDto.getCommentId())
                .orElseThrow(() -> new RuntimeException("Komentar nije pronađen"));

        Comment reply = new Comment();
        reply.setRepliesTo(commentToReply);
        reply.setUser(user);
        reply.setText(commentReplyDto.getContent());
        reply.setCreatedAt(LocalDateTime.now());
        reply.setDeleted(false);

        Comment savedReply = commentRepository.save(reply);

    }

}
