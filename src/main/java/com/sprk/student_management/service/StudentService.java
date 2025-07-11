package com.sprk.student_management.service;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    List<StudentDto> findAllStudents();

    StudentDto findStudentByRollNo(String rollNo);

    List<StudentDto> findStudentByGender(String gender);

    boolean deleteStudent(String rollNo);

    StudentDto updateStudent(String rollNo, StudentDto studentDto);

    // here we will write method definations only
}
