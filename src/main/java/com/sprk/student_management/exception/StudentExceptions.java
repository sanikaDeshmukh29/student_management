package com.sprk.student_management.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class StudentExceptions extends RuntimeException{

    public HttpStatus getStatus;
    private HttpStatus status;

    public StudentExceptions(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
