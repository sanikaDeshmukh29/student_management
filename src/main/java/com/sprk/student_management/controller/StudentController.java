package com.sprk.student_management.controller;


import com.sprk.student_management.dto.StudentDto;
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
    public StudentDto addStudent(@RequestBody StudentDto studentDto){

        StudentDto SavedStudent = studentService.saveStudent(studentDto);


        return SavedStudent;
    }

    // fetching all students
    @GetMapping("/students")
    public List<StudentDto> getAllStudents(){

        List<StudentDto> findAllStudents = studentService.findAllStudents();

        return findAllStudents;
    }

    // fetching student by roll number
    @GetMapping("/students/{rollNo}")
    public StudentDto findStudentByRollNo(@PathVariable int rollNo){

        StudentDto studentDto = studentService.findStudentByRollNo(rollNo);

        return studentDto;
    }

    @GetMapping("/gender-student")
    public List<StudentDto> findStudentByGender(@RequestParam String gender){

        List<StudentDto> studentsDtoList = studentService.findStudentByGender(gender);

        return studentsDtoList;
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

    @PutMapping("/students")
    public ResponseEntity<?> updateStudent(@RequestParam int rollNo, @RequestBody StudentDto studentDto){

        StudentDto updatedStudentDto = studentService.updateStudent(rollNo, studentDto);

       if(updatedStudentDto != null){
           return  ResponseEntity.ok(updatedStudentDto);
       }
       return ResponseEntity.ok(String.format("Student with roll number %d not found", rollNo));

    }

}
