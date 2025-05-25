package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.CommentRequestDto;
import com.example.todoplannerdev.dto.CommentResponseDto;
import com.example.todoplannerdev.entity.Comment;
import com.example.todoplannerdev.entity.Plan;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.exception.ForbiddenAccessException;
import com.example.todoplannerdev.exception.NotFoundException;
import com.example.todoplannerdev.repository.CommentRepository;
import com.example.todoplannerdev.repository.PlanRepository;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponseDto createComment(Long planId, CommentRequestDto dto, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Plan plan = planRepository.findById(planId).orElseThrow(NotFoundException::new);
        
        Comment comment = new Comment(dto.getContents(), user, plan);
        user.addComment(comment);
        plan.addComment(comment);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Override
    public List<CommentResponseDto> getPlanComments(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(NotFoundException::new);
        
        return plan.getComments().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto dto, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundException::new);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException();
        }

        comment.updateContents(dto.getContents());
        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NotFoundException::new);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ForbiddenAccessException();
        }

        commentRepository.delete(comment);
    }
}