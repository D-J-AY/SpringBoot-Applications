package com.JPA.hospitalManagement.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,"Username Not Found with Username: " + e.getMessage());
        return  new ResponseEntity<>(apiError,apiError.getStatus());
    }
}
