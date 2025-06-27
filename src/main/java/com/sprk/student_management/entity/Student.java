package com.sprk.student_management.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Student {

    private int rollNo;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private double percentage;


}
