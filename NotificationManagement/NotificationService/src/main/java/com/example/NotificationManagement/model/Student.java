package com.example.NotificationManagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Student {
    private int rollNum;
    private String studentName;
    private int age;
    private List<Address> address;

    @Override
    public String toString()
    {
        return "Student : { rollNum : "+this.rollNum+", studentName : "+this.studentName+", age : "+this.age+", address : "+this.address+"}";
    }

}