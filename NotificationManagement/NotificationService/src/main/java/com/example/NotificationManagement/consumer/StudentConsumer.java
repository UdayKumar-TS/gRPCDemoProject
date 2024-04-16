package com.example.NotificationManagement.consumer;

import com.example.NotificationManagement.model.Student;
import com.example.NotificationManagement.util.JsonToStudentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;
import com.example.NotificationManagement.util.ApplicationConstants;
import com.example.NotificationManagement.service.NotificationService;

import java.io.IOException;

@EnableKafka
@Service
public class StudentConsumer {

    @Autowired
    private JsonToStudentConverter jsonToStudentConverter;
    private Logger log  = LoggerFactory.getLogger(StudentConsumer.class);
    @Autowired
    private NotificationService notificationService;

    @KafkaListener(id = "consumer1" ,topicPartitions = {@TopicPartition(topic = ApplicationConstants.ADD_STUDENT_TOPIC,partitions = {"0"})})
    public void consumeAddStudent1(String student)
    {
        try
        {
            Student student1 = jsonToStudentConverter.convert(student);
            log.info("Consumer1");
            log.info(student1.toString());
            notificationService.sendNotification(ApplicationConstants.ADD_STUDENT_NOTIFICATION_TYPE,student1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @KafkaListener(id = "container2",topicPartitions = {@TopicPartition(topic = ApplicationConstants.ADD_STUDENT_TOPIC,partitions = {"1"})})
    public void consumeAddStudent2(String student)
    {
        try
        {
            Student student1 = jsonToStudentConverter.convert(student);
            log.info("Consumer2");
            log.info(student1.toString());
            notificationService.sendNotification(ApplicationConstants.ADD_STUDENT_NOTIFICATION_TYPE,student1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics=ApplicationConstants.DELETE_STUDENT_TOPIC, groupId = "0544")
    public void consumeDeletedStudent(String jsonStudent) {
        try {
            Student student = jsonToStudentConverter.convert(jsonStudent);
            log.info(student.toString());
            notificationService.sendNotification(ApplicationConstants.DELETE_STUDENT_NOTIFICATION_TYPE,student);
            //notificationProducer.produceDeletedStudentNotification("deletedStudentNotification",notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = ApplicationConstants.UPDATE_STUDENT_TOPIC, groupId = "054")
    public void consumeUpdatedStudent(String jsonStudent) {
        try {
            Student student = jsonToStudentConverter.convert(jsonStudent);
            log.info(student.toString() + " consumer 1");
            notificationService.sendNotification(ApplicationConstants.UPDATE_STUDENT_TOPIC,student);
            //notificationProducer.produceUpdatedStudentNotification("deletedStudentNotification",notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = ApplicationConstants.UPDATE_STUDENT_TOPIC, groupId = "0544")
    public void consumeUpdatedStudent1(String jsonStudent) {
        try {
            Student student = jsonToStudentConverter.convert(jsonStudent);
            log.info(student.toString() + " consumer 2");
            notificationService.sendNotification(ApplicationConstants.UPDATE_STUDENT_TOPIC,student);
            //notificationProducer.produceUpdatedStudentNotification("deletedStudentNotification",notification);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
