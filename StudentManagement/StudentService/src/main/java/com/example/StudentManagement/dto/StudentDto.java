package com.example.StudentManagement.dto;




import com.example.StudentManagement.model.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@Getter
public class StudentDto
{
    private int rollNum;
    private String studentName;
    private int age;
    private List<Address> address;

    @Override
    public String toString()
    {
        return "Student:{ studentName : "+this.studentName+" age : "+ this.age +" address : "+this.address +"}";
    }
}
