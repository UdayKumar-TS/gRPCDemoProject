package com.example.NotificationManagement.service.impl;

import com.example.NotificationManagement.exception.NotificationNotFoundException;
import com.example.NotificationManagement.model.Notification;
import com.example.NotificationManagement.model.Student;
import com.example.NotificationManagement.repository.NotificationRepository;
import com.example.NotificationManagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService
{
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private MongoOperations mongoOperations;
    @Override
    public void sendNotification(String notificationType, Student student)
    {
        Notification notification = new Notification();
        notification.setNotificationId(getNextNotificationId());
        notification.setNotificationType(notificationType);
        notification.setPayload(student);
        notification.setCreatedOn(LocalDate.now());
        notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(int notificationId)
    {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (notification.isPresent())
        {
            return notification.get();
        }
       return null;
    }

    @Override
    public List<Notification> getNotificationsWithPagination(int pageNum,int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return notificationRepository.findAll(pageable).getContent();
    }
    public List<Notification> getNotificationsWithSorting(int pageNum,int pageSize,String sortField,String direction)
    {
        Sort.Direction direction1 = Sort.Direction.ASC;
        if(direction.equalsIgnoreCase("desc"))
        {
            direction1 = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,direction1,sortField);
        return notificationRepository.findAll(pageable).getContent();
    }
    private int getNextNotificationId() {
        Query query = new Query().with(Sort.by(Sort.Direction.DESC, "notificationId")).limit(1);
        Notification lastDocument = mongoOperations.findOne(query, Notification.class);
        if (lastDocument != null) {
            return lastDocument.getNotificationId() + 1;
        } else {
            // If no document found, start from 1
            return 1;
        }
    }
}
