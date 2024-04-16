package com.example.NotificationManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class NotificationExceptionHandler {

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<ErrorResponse> notificationNotFoundException(NotificationNotFoundException e)
    {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDate.now().toString(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
