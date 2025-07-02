package com.sprk.student_management.controller;


import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sprk")
public class StudentController {

    public final StudentService studentService;

    // inserting student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){

        Student SavedStudent = studentService.saveStudent(student);


        return SavedStudent;
    }

    // fetching all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){

        List<Student> findAllStudents = studentService.findAllStudents();

        return findAllStudents;
    }

    // fetching student by roll number
    @GetMapping("/students/{rollNo}")
    public Student findStudentByRollNo(@PathVariable int rollNo){

        Student student = studentService.findStudentByRollNo(rollNo);

        return student;
    }

    @GetMapping("/gender-student")
    public List<Student> findStudentByGender(@RequestParam String gender){

        List<Student> students = studentService.findStudentByGender(gender);

        return students;
    }

    @DeleteMapping("/students")
    public ResponseEntity<String> deleteStudent(@RequestParam int rollNo){

        boolean isDeleted = studentService.deleteStudent(rollNo);

        if(isDeleted){

            String msg = String.format("Student with roll number %d deleted successfully ! ", rollNo);

            //ResponseEntity.status(HttpStatus.OK);
            //ResponseEntity.status(200).body(msg);
            return ResponseEntity.ok(msg);


        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Student with roll number %d not found", rollNo));

    }

    

}
