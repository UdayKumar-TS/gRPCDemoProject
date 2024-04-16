package com.example.StudentManagement;

import com.example.StudentManagement.util.JsonToNotificationConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class,args);
    }
    @Bean
    public JsonToNotificationConverter jsonToNotificationConverter()
    {
        return new JsonToNotificationConverter();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}