package com.example.NotificationManagement.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "Notification")
public class Notification {

    @Id
    private int notificationId;
    private String notificationType;
    private Student payload;
    private LocalDate createdOn;
}
