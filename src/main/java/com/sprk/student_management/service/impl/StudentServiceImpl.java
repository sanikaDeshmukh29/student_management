package com.sprk.student_management.service.impl;

import com.sprk.student_management.Repository.StudentRepository;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    // Here we will write business logic

    //Dependency Injection

    public final StudentRepository studentRepository;


    @Override
    public Student saveStudent(Student student) {
       Student savedStudent =  studentRepository.save(student);

        return savedStudent;
    }

    @Override
    public List<Student> findAllStudents() {

        List<Student> students = studentRepository.findAll();
        return students;
    }

    @Override
    public Student findStudentByRollNo(int rollNo) {

        Student student = studentRepository.findById(rollNo).orElseThrow(() ->
                new RuntimeException(String.format("the student with roll number %d not found!", rollNo)));
        return student;


    }

    @Override
    public List<Student> findStudentByGender(String gender) {

        List<Student> students = studentRepository.findByGender(gender);

        return students;
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
    public Student updateStudent(int rollNo, Student student) {

        // check if student exsist or not

        Student existStudent = studentRepository.findById(rollNo).orElse(null);

        // if exist then update

        if(existStudent != null){

            student.setRollNo(rollNo);
           Student updatedStudent = studentRepository.save(student);
            return  updatedStudent;

        }
        return  existStudent;



    }
}
