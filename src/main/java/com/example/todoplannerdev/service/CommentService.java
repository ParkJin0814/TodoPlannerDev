package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.CommentRequestDto;
import com.example.todoplannerdev.dto.CommentResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(Long planId, @Valid CommentRequestDto dto, Long userId);

    List<CommentResponseDto> getPlanComments(Long planId);

    CommentResponseDto updateComment(Long commentId, CommentRequestDto dto, Long userId);

    void deleteComment(Long commentId, Long userId);
}
