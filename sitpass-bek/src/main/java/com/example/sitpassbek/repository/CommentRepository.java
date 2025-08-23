package com.example.sitpassbek.repository;

import com.example.sitpassbek.model.Comment;
import com.example.sitpassbek.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
