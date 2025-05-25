package com.example.todoplannerdev.controller;

import com.example.todoplannerdev.common.LoginConst;
import com.example.todoplannerdev.dto.CommentRequestDto;
import com.example.todoplannerdev.dto.CommentResponseDto;
import com.example.todoplannerdev.dto.LoginResponseDto;
import com.example.todoplannerdev.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan/{planId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long planId,
            @Valid @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        LoginResponseDto loginUser = (LoginResponseDto)session.getAttribute(LoginConst.LOGIN_USER);
        Long userId = loginUser.getId();
        return new ResponseEntity<>(commentService.createComment(planId, dto, userId), HttpStatus.CREATED);
    }

    // 댓글조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getPlanComments(
            @PathVariable Long planId
    ) {
        return new ResponseEntity<>(commentService.getPlanComments(planId), HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto dto,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        LoginResponseDto loginUser = (LoginResponseDto)session.getAttribute(LoginConst.LOGIN_USER);
        Long userId = loginUser.getId();

        return new ResponseEntity<>(commentService.updateComment(commentId, dto, userId), HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long planId,
            @PathVariable Long commentId,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();

        LoginResponseDto loginUser = (LoginResponseDto)session.getAttribute(LoginConst.LOGIN_USER);
        Long userId = loginUser.getId();

        commentService.deleteComment(commentId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}