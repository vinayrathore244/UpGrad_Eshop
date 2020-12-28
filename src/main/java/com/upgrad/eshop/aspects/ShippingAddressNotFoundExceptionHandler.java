package com.upgrad.eshop.aspects;

import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShippingAddressNotFoundExceptionHandler {
    @ExceptionHandler(ShippingAddressNotFoundException.class)
    public ResponseEntity<?> handleShippingAddressNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
