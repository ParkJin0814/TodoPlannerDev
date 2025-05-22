package com.example.todoplannerdev.controller;

import com.example.todoplannerdev.dto.*;
import com.example.todoplannerdev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    // 일정생성
    @PostMapping
    public ResponseEntity<UserResponseDto> createPlan(@RequestBody UserRequestDto dto) {
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

    // 일정삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
