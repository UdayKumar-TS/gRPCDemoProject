package com.example.NotificationManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationNotFoundException extends RuntimeException
{
    private String message;
}
