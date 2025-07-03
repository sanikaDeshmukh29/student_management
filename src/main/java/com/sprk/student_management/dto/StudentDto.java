package com.sprk.student_management.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class StudentDto {


    private int rollNo;


    private String firstName;


    private String lastName;


    private int age;


    private String gender;


    private String address;


    private double percentage;
}
