package com.example.todoplannerdev.repository;

import com.example.todoplannerdev.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
