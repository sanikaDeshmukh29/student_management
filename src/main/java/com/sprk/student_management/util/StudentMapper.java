package com.sprk.student_management.util;

import com.sprk.student_management.dto.StudentDto;
import com.sprk.student_management.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto mapStudentToStudentDto(Student student);

    Student mapStudentDtoToStudent(StudentDto studentDto);
}