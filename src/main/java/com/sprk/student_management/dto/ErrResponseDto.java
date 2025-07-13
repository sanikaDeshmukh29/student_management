package com.sprk.student_management.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrResponseDto<E> {

    private String apiPath;

    private HttpStatus status;

    private E message;

    private LocalDateTime timestamp;


}

