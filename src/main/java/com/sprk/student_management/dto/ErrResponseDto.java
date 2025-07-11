package com.sprk.student_management.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrResponseDto {

    private String apiPath;

    private HttpStatus status;

    private String message;

    private LocalDateTime timestamp;


}

