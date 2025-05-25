package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.*;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);

    UserResponseDto findUserById(Long userId);

    UserResponseDto updateUser(Long userId, UserUpdateRequestDto dto);

    void deleteUser(Long userId);

    LoginResponseDto login(LoginRequestDto dto);
}
