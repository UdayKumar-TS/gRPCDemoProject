package com.example.StudentManagement.controller;

import com.example.StudentManagement.dto.StudentDto;
import com.example.StudentManagement.exception.DuplicateStudentException;
import com.example.StudentManagement.exception.StudentNotFoundException;
import com.example.StudentManagement.model.Notification;
import com.example.StudentManagement.model.Student;
import com.example.StudentManagement.service.StudentService;
import com.google.protobuf.Descriptors;
import com.proto.NotificationRequest;
import com.proto.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody StudentDto studentDto)
    {
        if (studentService.addStudent(studentDto))
        {
            return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
        }
        else
        {
            throw new DuplicateStudentException("Student with rollNum "+studentDto.getRollNum()+" already exist");
        }
    }
    @GetMapping
    public ResponseEntity<Student> getStudent(@RequestParam int rollNum)
    {
        Student student = studentService.getStudent(rollNum);
        if(student !=null)
        {
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
        else
        {
            throw new StudentNotFoundException("Student with rollNum "+rollNum+" not found");
        }
    }
    @DeleteMapping
    public  ResponseEntity<String> deleteStudent(@RequestParam int rollNum)
    {
        if(studentService.deleteStudent(rollNum))
        {
            return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
        }
        else {
            throw new StudentNotFoundException("Student with rollNum "+rollNum+" not found");
        }
    }
    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody StudentDto studentDto)
    {
        if(studentService.updateStudent(studentDto))
        {
            return  new ResponseEntity<>("updated successfully",HttpStatus.OK);
        }
        else
        {
            throw new StudentNotFoundException("Student with rollNum "+studentDto.getRollNum()+" not found");
        }
    }

    @GetMapping("/unique-cities")
    public ResponseEntity<List<String>> getUniqueCities()
    {
        return new ResponseEntity<>(studentService.getUniqueCities(),HttpStatus.OK);
    }
    @GetMapping("/notification-by-id")
    public Map<Descriptors.FieldDescriptor,Object> getNotificationById(@RequestParam int notificationId)
    {
        NotificationRequest request = NotificationRequest.newBuilder().setNotificationId(notificationId).build();
        return studentService.getNotificationById(request);
    }

    @GetMapping("/paginated")
    public List<Notification> getNotificationsWithPagination(@RequestParam int pageNum,@RequestParam int pageSize)
    {
        return studentService.getNotificationsWithPagination(pageNum,pageSize);
    }

    @GetMapping("/sorted")
    public List<Notification> getNotificationWithSorting(@RequestParam int pageNum,@RequestParam int pageSize,@RequestParam String sortField,@RequestParam String direction)
    {
        return studentService.getNotificationWithSorting(pageNum,pageSize,sortField,direction);
    }

}
