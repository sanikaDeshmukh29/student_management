package com.sprk.student_management.exception;

import org.springframework.http.HttpStatus;

public class StudentRollNoNotFoundException extends StudentExceptions{


    public StudentRollNoNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
