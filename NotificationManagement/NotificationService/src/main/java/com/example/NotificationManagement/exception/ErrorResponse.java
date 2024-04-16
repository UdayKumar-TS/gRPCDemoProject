package com.example.NotificationManagement.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse
{
    private String message;
    private String timeStamp;
    private HttpStatus errorCode;
}
