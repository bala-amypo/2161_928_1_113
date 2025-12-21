package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handle(RuntimeException ex) {
        return new ApiResponse(false, ex.getMessage());
    }
}
