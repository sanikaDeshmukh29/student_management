package com.sprk.student_management.exception.advice;

import com.sprk.student_management.exception.StudentExceptions;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExist extends StudentExceptions {
    public EmailAlreadyExist(String message, HttpStatus status) {
        super(message, status);
    }
}
