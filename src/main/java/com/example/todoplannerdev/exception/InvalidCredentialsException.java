package com.example.todoplannerdev.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BaseException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 맞지않습니다.");
    }
}
