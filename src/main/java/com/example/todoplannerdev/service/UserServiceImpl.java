package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.*;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.exception.InvalidCredentialsException;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User(dto.getName(), dto.getEmail(), dto.getPassword());
        userRepository.save(user);

        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow();

        user.updateName(dto.getName());

        return new UserResponseDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        userRepository.delete(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(InvalidCredentialsException::new);
        // 비밀번호가 틀릴경우
        if (!user.getPassword().equals(dto.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return new LoginResponseDto(user.getId());
    }
}
