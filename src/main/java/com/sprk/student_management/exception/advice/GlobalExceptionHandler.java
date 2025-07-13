package com.sprk.student_management.exception.advice;

import com.sprk.student_management.dto.ErrResponseDto;
import com.sprk.student_management.exception.StudentExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        //Extract message

        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.forEach(error -> {

            String fieldName = ((FieldError)error).getField();
            String errorMessage = ((FieldError)error).getDefaultMessage();

            validationErrors.put(fieldName,errorMessage);
        });

        ErrResponseDto<Map<String,String>> errResponseDto = new ErrResponseDto();

        errResponseDto.setApiPath(request.getDescription(false));

        errResponseDto.setTimestamp(LocalDateTime.now());

        errResponseDto.setMessage(validationErrors);

        errResponseDto.setStatus(HttpStatus.valueOf(status.value()));

        return new ResponseEntity<>(errResponseDto, HttpStatus.valueOf(status.value()));



    }

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
