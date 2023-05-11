package com.example.springsercurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorMessage> customExceptionHandler(CustomException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getStatus().value(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatus());
    }

}
