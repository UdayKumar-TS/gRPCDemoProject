package com.example.NotificationManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Sequence {
    @Id
    private  String id;
    private int seqNo;
}
