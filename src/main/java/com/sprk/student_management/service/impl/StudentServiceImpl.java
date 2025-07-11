package com.sprk.student_management.service.impl;

import com.sprk.student_management.Repository.StudentRepository;
import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.exception.StudentRollNoMismatch;
import com.sprk.student_management.exception.StudentRollNoNotFoundException;
import com.sprk.student_management.service.StudentService;
import com.sprk.student_management.util.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    // Here we will write business logic

    //Dependency Injection

    public final StudentRepository studentRepository;


    @Override
    public StudentDto saveStudent(StudentDto studentDto) {

        // before passing to repo convert it to entity
       Student student = StudentMapper.studentDtoToStudent(studentDto);
       Student savedStudent =  studentRepository.save(student);

       //after repo operation convert again to dto before sending to controller or client
        StudentDto saveStudentDto = StudentMapper.studentToStudentDto(student);

        return saveStudentDto;
    }

    @Override
    public List<StudentDto> findAllStudents() {
       // get all students first
        List<Student> students = studentRepository.findAll();

        // then convert to dto
        List<StudentDto> allStudentsDto = new ArrayList<>();

        for(Student student : students){
           StudentDto studentDto = StudentMapper.studentToStudentDto(student);
           allStudentsDto.add(studentDto);
        }
        return allStudentsDto;
    }

    @Override
    public StudentDto findStudentByRollNo(String rollNo) {

        if(!Pattern.matches("^\\d+$", rollNo)){

          throw new StudentRollNoMismatch("Enter roll number in integers only !", HttpStatus.BAD_REQUEST);
        }

        int intRollNo = Integer.parseInt(rollNo);

        Student student = studentRepository
                .findById(intRollNo).
                orElseThrow(() -> new StudentRollNoNotFoundException(String.format("the student with roll number %d not found!", intRollNo), HttpStatus.NOT_FOUND));

        // convert it to dto before passing

        StudentDto studentDto = StudentMapper.studentToStudentDto(student);
        return studentDto;


    }

    @Override
    public List<StudentDto> findStudentByGender(String gender) {

        // we will get all students by gender
        List<Student> students = studentRepository.findByGender(gender);

        // now convert to dto
        List<StudentDto> studentDtoList = new ArrayList<>();
        for(Student student : students){
            StudentDto studentDto = StudentMapper.studentToStudentDto(student);
            studentDtoList.add(studentDto);
        }

        return studentDtoList;
    }

    @Override
    public boolean deleteStudent(String rollNo) {
        // check first - student exists or not

        if(!Pattern.matches("^[\\d]+$", rollNo)){

            throw new StudentRollNoMismatch("Enter roll number in integers only !", HttpStatus.BAD_REQUEST);
        }

        int intRollNo = Integer.parseInt(rollNo);

        Student existStudent = studentRepository
                .findById(intRollNo). orElseThrow(() -> new StudentRollNoNotFoundException(String.format("the student with roll number %d not found!", intRollNo), HttpStatus.NOT_FOUND));

        // if exist then delete
        if(existStudent != null){
            //delete
            studentRepository.delete(existStudent);

            return true;

        }
        return false;
    }

    @Override
    public StudentDto updateStudent(String rollNo, StudentDto studentDto) {

        // check if student exsist or not

        if(!Pattern.matches("^\\d+$", rollNo)){
            throw new StudentRollNoMismatch("Enter roll number in integers only !", HttpStatus.BAD_REQUEST);
        }

        int intRollNo = Integer.parseInt(rollNo);

        //convert to dto before passing to repo
        Student student = StudentMapper.studentDtoToStudent(studentDto);

        Student existStudent = studentRepository.findById(intRollNo).orElseThrow(() -> new StudentRollNoNotFoundException(String.format("the student with roll number %d not found!", intRollNo), HttpStatus.NOT_FOUND));

        // if exist then update
            student.setRollNo(intRollNo);
            Student updatedStudent = studentRepository.save(student);

           // now convert to dto again

            StudentDto updatedStudentDto = StudentMapper.studentToStudentDto(updatedStudent);
            return  updatedStudentDto;

    }
}
