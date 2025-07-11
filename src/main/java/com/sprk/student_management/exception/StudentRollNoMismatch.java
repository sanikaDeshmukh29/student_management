package com.sprk.student_management.exception;

import org.springframework.http.HttpStatus;

public class StudentRollNoMismatch extends StudentExceptions{

    public StudentRollNoMismatch(String message, HttpStatus status) {

        super(message,status);
    }
}
