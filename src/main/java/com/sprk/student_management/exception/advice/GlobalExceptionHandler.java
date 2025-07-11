package com.sprk.student_management.exception.advice;

import com.sprk.student_management.dto.ErrResponseDto;
import com.sprk.student_management.exception.StudentExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(StudentExceptions.class)
    public ResponseEntity<ErrResponseDto> handleMethodArgumentTypeMismatchException(StudentExceptions e, WebRequest request){

    ErrResponseDto errResponseDto = new ErrResponseDto();

    errResponseDto.setMessage(e.getMessage());

    errResponseDto.setApiPath(request.getDescription(false));

    errResponseDto.setStatus(e.getStatus());

    errResponseDto.setTimestamp(LocalDateTime.now());

    return ResponseEntity.status(e.getStatus()).body(errResponseDto);
}
}
