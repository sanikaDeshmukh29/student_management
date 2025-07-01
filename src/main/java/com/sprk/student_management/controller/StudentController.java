package com.sprk.student_management.controller;


import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sprk")
public class StudentController {

    public final StudentService studentService;

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        Student SavedStudent = studentService.saveStudent(student);


        return SavedStudent;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){

        List<Student> findAllStudents = studentService.findAllStudents();

        return findAllStudents;
    }

}
