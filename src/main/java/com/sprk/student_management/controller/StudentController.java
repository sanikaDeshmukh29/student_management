package com.sprk.student_management.controller;


import com.sprk.student_management.Repository.StudentRepository;
import com.sprk.student_management.constants.StudentConstants;
import com.sprk.student_management.dto.ResponseDto;
import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import com.sprk.student_management.util.StudentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sprk")
public class StudentController {

    public final StudentService studentService;

    private final StudentMapper studentMapper;

    private final StudentRepository studentRepository;


    // inserting student
    @PostMapping("/students")
    public ResponseEntity<ResponseDto<StudentDto>> addStudent(@Valid @RequestBody StudentDto studentDto){

        StudentDto savedStudent = studentService.saveStudent(studentDto);

        ResponseDto<StudentDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.STUDENT_CREATED);
        responseDto.setMessage(String.format(StudentConstants.STUDENT_CREATED_MSG,savedStudent.getRollNo()));
        responseDto.setData(savedStudent);

        return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.STUDENT_CREATED)).body(responseDto);

    }

    // fetching all students
    @GetMapping("/students")
    public ResponseEntity<ResponseDto<List<StudentDto>>> getAllStudents(){

        List<StudentDto> studentsDto = studentService.findAllStudents();

        ResponseDto<List<StudentDto>> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.SUCCESS);
        responseDto.setMessage(StudentConstants.GET_ALL_STUDENT);
        responseDto.setData(studentsDto);

        return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.SUCCESS)).body(responseDto);
    }

    // fetching student by roll number
    @GetMapping("/students/{rollNo}")
    public ResponseEntity<ResponseDto<StudentDto>> findStudentByRollNo(@PathVariable String rollNo){

        StudentDto studentDto = studentService.findStudentByRollNo(rollNo);

        ResponseDto<StudentDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.SUCCESS);
        responseDto.setMessage(String.format(StudentConstants.STUDENT_FOUND_WITH_ROLL_NO,studentDto.getRollNo()));
        responseDto.setData(studentDto);

        return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.SUCCESS)).body(responseDto);


    }

    @GetMapping("/gender-student")
    public ResponseEntity<ResponseDto<List<StudentDto>>> findStudentByGender(@RequestParam String gender){

        List<StudentDto> studentsDtoList = studentService.findStudentByGender(gender);
        ResponseDto <List<StudentDto>> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.SUCCESS);
        responseDto.setMessage(String.format(StudentConstants.STUDENT_FOUND_WITH_GENDER,gender));
        responseDto.setData(studentsDtoList);

        return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.SUCCESS)).body(responseDto);

    }

    @DeleteMapping("/students")
    public ResponseEntity<ResponseDto<String>> deleteStudent(@RequestParam String rollNo){

        boolean isDeleted = studentService.deleteStudent(rollNo);

        ResponseDto<String> responseDto = new ResponseDto<>();


        if(isDeleted){

            responseDto.setStatusCode(StudentConstants.SUCCESS);
            responseDto.setMessage(String.format(StudentConstants.STUDENT_DELETED_SUCCESSFULLY,rollNo));
            responseDto.setData(rollNo);

            return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.SUCCESS)).body(responseDto);
        }
            responseDto.setStatusCode(StudentConstants.NOT_FOUND);
            responseDto.setMessage(String.format(StudentConstants.ROLL_NO_NOT_FOUND, rollNo));
            responseDto.setData(null);
            return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.NOT_FOUND)).body(responseDto);
        }

    @PutMapping("/students")
    public ResponseEntity<ResponseDto<StudentDto>> updateStudent(@RequestParam String rollNo, @RequestBody StudentDto studentDto){

        StudentDto updatedStudentDto = studentService.updateStudent(rollNo, studentDto);

       if(updatedStudentDto != null){

           ResponseDto<StudentDto> responseDto = new ResponseDto<>();
           responseDto.setStatusCode(StudentConstants.SUCCESS);
           responseDto.setMessage(String.format(StudentConstants.STUDENT_UPDATED_SUCCESSFULLY,rollNo));
           responseDto.setData(updatedStudentDto);

           return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.SUCCESS)).body(responseDto);

       }

        ResponseDto<StudentDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(StudentConstants.NOT_FOUND);
        responseDto.setMessage(String.format(StudentConstants.STUDENT_FOUND_WITH_ROLL_NO,studentDto.getRollNo()));
        responseDto.setData(null);

        return ResponseEntity.status(HttpStatusCode.valueOf(StudentConstants.NOT_FOUND)).body(responseDto);


    }

}
