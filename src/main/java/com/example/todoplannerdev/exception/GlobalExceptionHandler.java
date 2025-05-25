package com.example.todoplannerdev.exception;

import com.example.todoplannerdev.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PlannerException.class, InvalidCredentialsException.class})
    public ResponseEntity<ErrorResponseDto> handlePlannerException(PlannerException e) {
        ErrorResponseDto response = new ErrorResponseDto(e.getStatus(), e.getMessage());
        return new ResponseEntity<>(response, e.getStatus());
    }
}
