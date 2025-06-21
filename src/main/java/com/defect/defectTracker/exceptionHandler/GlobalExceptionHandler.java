package com.defect.defectTracker.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReleaseTestCaseBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(ReleaseTestCaseBadRequestException ex) {
        ErrorResponse error = new ErrorResponse(4000, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReleaseTestCaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ReleaseTestCaseNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(4000, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
