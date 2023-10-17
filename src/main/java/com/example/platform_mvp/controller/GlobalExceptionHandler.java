package com.example.platform_mvp.controller;

import com.example.platform_mvp.validation.ExceptionEntity;
import com.example.platform_mvp.validation.ExceptionResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstrainViolationExpedition(ConstraintViolationException e) {
        List<ExceptionEntity> exceptionEntities = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage())
                .map(ExceptionEntity::new)
                .toList();
        return new ResponseEntity<>(new ExceptionResponse(exceptionEntities), HttpStatus.BAD_REQUEST);
    }

}
