package com.example.todoplannerdev.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenAccessException extends BaseException {
    public ForbiddenAccessException() {
        super(HttpStatus.FORBIDDEN, "권한이 없습니다");
    }
}
