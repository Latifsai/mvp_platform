package com.example.platform_mvp.controller;

import com.example.platform_mvp.validation.ExceptionEntity;
import com.example.platform_mvp.validation.ExceptionResponse;
import com.example.platform_mvp.validation.exceptions.AlreadyExistException;
import com.example.platform_mvp.validation.exceptions.FormatException;
import com.example.platform_mvp.validation.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstrainViolationException(ConstraintViolationException e) {
        List<ExceptionEntity> exceptionEntities = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage())
                .map(ExceptionEntity::new)
                .toList();
        return new ResponseEntity<>(new ExceptionResponse(exceptionEntities), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        List<ExceptionEntity> exceptionEntities = List.of(new ExceptionEntity(e.getMessage()));
        return new ResponseEntity<>(new ExceptionResponse(exceptionEntities), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistException(AlreadyExistException e) {
        List<ExceptionEntity> exceptionEntities = List.of(new ExceptionEntity(e.getMessage()));
        return new ResponseEntity<>(new ExceptionResponse(exceptionEntities), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FormatException.class)
    public ResponseEntity<?> handleFormatException(FormatException e) {
        List<ExceptionEntity> exceptionEntities = List.of(new ExceptionEntity(e.getMessage()));
        return new ResponseEntity<>(new ExceptionResponse(exceptionEntities), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        List<ExceptionEntity> exceptionEntities = List.of(new ExceptionEntity(e.getMessage()));
        return new ResponseEntity<>(new ExceptionResponse(exceptionEntities), HttpStatus.BAD_REQUEST);
    }

}
