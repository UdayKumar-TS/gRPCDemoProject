package com.example.NotificationManagement.repository;

import com.example.NotificationManagement.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification,Integer>
{

}
