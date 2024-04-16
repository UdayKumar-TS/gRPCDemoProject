package com.example.StudentManagement.service;

import com.example.StudentManagement.dto.StudentDto;
import com.example.StudentManagement.model.Notification;
import com.example.StudentManagement.model.Student;
import com.google.protobuf.Descriptors;
import com.proto.NotificationRequest;
import com.proto.NotificationResponse;

import java.util.List;
import java.util.Map;

public interface StudentService
{
    boolean addStudent(StudentDto studentDto);
    Student getStudent(int rollNum);

    boolean deleteStudent(int rollNum);
    boolean updateStudent(StudentDto studentDto);

    List<String> getUniqueCities();
    Map<Descriptors.FieldDescriptor,Object> getNotificationById(NotificationRequest request);

    List<Notification>getNotificationsWithPagination(int pageNum, int pageSize);
    List<Notification> getNotificationWithSorting(int pageNum, int pageSize,String sortField,String direction);
}