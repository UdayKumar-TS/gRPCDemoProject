package com.example.NotificationManagement.util;

import com.example.NotificationManagement.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonToStudentConverter {

    @Autowired
    private ObjectMapper objectMapper;
    public Student convert(String jsonStudent) throws IOException
    {
        return objectMapper.readValue(jsonStudent, new TypeReference<Student>() {});
    }
}
