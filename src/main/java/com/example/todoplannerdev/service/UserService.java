package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.UserRequestDto;
import com.example.todoplannerdev.dto.UserResponseDto;
import com.example.todoplannerdev.dto.UserUpdateRequestDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);

    UserResponseDto findUserById(Long userId);

    UserResponseDto updateUser(Long userId, UserUpdateRequestDto dto);

    void deleteUser(Long userId);
}
