package com.sprk.student_management.service.impl;

import com.sprk.student_management.Repository.StudentRepository;
import com.sprk.student_management.entity.Student;
import com.sprk.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
