package com.example.todoplannerdev.controller;

import com.example.todoplannerdev.common.LoginConst;
import com.example.todoplannerdev.dto.*;
import com.example.todoplannerdev.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    // 유저생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createPlan(@Valid @RequestBody UserRequestDto dto) {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    // 고유식별자로 단건조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findPlanById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    // 내용수정
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateContentPlanById(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequestDto dto
    ) {
        return new ResponseEntity<>(userService.updateUser(userId, dto), HttpStatus.OK);
    }

    // 유저삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {
        LoginResponseDto loginUser = userService.login(dto);
        HttpSession session = request.getSession();

        session.setAttribute(LoginConst.LOGIN_USER, loginUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
