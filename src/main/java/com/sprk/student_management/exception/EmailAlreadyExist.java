package com.sprk.student_management.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExist extends StudentExceptions {
    public EmailAlreadyExist(String message, HttpStatus status) {
        super(message, status);
    }
}
