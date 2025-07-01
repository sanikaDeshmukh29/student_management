package com.sprk.student_management.controller;


import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
