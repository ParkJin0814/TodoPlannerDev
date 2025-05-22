package com.example.todoplannerdev.service;

import com.example.todoplannerdev.dto.UserRequestDto;
import com.example.todoplannerdev.dto.UserResponseDto;
import com.example.todoplannerdev.dto.UserUpdateRequestDto;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
