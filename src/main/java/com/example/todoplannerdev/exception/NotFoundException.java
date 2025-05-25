package com.example.todoplannerdev.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "요청하신 정보를 찾을 수 없습니다");
    }
}
