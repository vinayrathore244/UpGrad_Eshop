package com.upgrad.eshop.aspects;

import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsernameAlreadyRegisteredExceptionHandler {
    @ExceptionHandler(UsernameAlreadyRegisteredException.class)
    public ResponseEntity<?> handleUsernameAlreadyRegisteredException (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
    }
}
