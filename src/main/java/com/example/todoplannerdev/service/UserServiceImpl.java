package com.example.todoplannerdev.service;

import com.example.todoplannerdev.config.PasswordEncoder;
import com.example.todoplannerdev.dto.*;
import com.example.todoplannerdev.entity.User;
import com.example.todoplannerdev.exception.EmailAlreadyExistsException;
import com.example.todoplannerdev.exception.InvalidCredentialsException;
import com.example.todoplannerdev.exception.NotFoundException;
import com.example.todoplannerdev.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        // 비밀번호 암호화 추가
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getName(), dto.getEmail(), encodedPassword);
        userRepository.save(user);

        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        user.updateName(dto.getName());

        return new UserResponseDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        userRepository.delete(user);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        // matches 메서드로 비밀번호 검증
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }


        return new LoginResponseDto(user.getId());
    }
}
