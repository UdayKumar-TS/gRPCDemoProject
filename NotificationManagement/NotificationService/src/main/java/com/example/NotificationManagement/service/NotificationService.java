package com.example.NotificationManagement.service;

import com.example.NotificationManagement.model.Notification;
import com.example.NotificationManagement.model.Student;

import java.util.List;

public interface NotificationService
{
    void sendNotification(String notificationType, Student student);
    Notification getNotificationById(int notificationId);
    List<Notification> getNotificationsWithPagination(int pageNum,int pageSize);

    List<Notification> getNotificationsWithSorting(int pageNum,int pageSize,String sortField,String direction);
}