package com.sprk.student_management.service.impl;

import com.sprk.student_management.Repository.StudentRepository;
import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import com.sprk.student_management.util.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public StudentDto findStudentByRollNo(int rollNo) {

        Student student = studentRepository.findById(rollNo).orElseThrow(() ->
                new RuntimeException(String.format("the student with roll number %d not found!", rollNo)));

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
    public boolean deleteStudent(int rollNo) {
        // check first - student exists or not

        Student existStudent = studentRepository.findById(rollNo).orElse(null);

        // if exist then delete
        if(existStudent != null){
            //delete
            studentRepository.delete(existStudent);

            return true;

        }
        return false;
    }

    @Override
    public StudentDto updateStudent(int rollNo, StudentDto studentDto) {

        // check if student exsist or not
        //convert to dto before passing to repo
        Student student = StudentMapper.studentDtoToStudent(studentDto);

        Student existStudent = studentRepository.findById(rollNo).orElse(null);
        StudentDto exsitingStudentDto = StudentMapper.studentToStudentDto(existStudent);

        // if exist then update

        if(existStudent != null){

            student.setRollNo(rollNo);
           Student updatedStudent = studentRepository.save(student);

           // now convert to dto again

            StudentDto updatedStudentDto = StudentMapper.studentToStudentDto(updatedStudent);
            return  updatedStudentDto;

        }
        return  exsitingStudentDto;



    }
}
