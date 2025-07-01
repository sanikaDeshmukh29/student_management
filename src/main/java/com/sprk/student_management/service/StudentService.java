package com.sprk.student_management.service;

import com.sprk.student_management.entity.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> findAllStudents();

    // here we will write method definations only
}
