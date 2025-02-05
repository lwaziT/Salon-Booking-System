package com.lwazi.user_service.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.lwazi.user_service.payload.ExceptionResponse;

@ControllerAdvice
public class globalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e,
            WebRequest request) {
        
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(), 
                request.getDescription(false), 
                LocalDateTime.now()
        );
        
        
        return ResponseEntity.ok(response);

    } 
}
