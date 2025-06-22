// src/main/java/com/example/defectTracker/exception/GlobalExceptionHandler.java
package com.defect.defectTracker.exceptionHandler;


import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.utils.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Constants.responseBuilder(
                        HttpStatus.NOT_FOUND.value(),
                        Constants.STATUS_FAILURE,
                        Constants.DATA_NOT_FOUND,
                        ex.getMessage(),
                        null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest()
                .body(Constants.responseBuilder(
                        HttpStatus.BAD_REQUEST.value(),
                        Constants.STATUS_FAILURE,
                        Constants.RETRIEVE_FAILED,
                        ex.getMessage(),
                        null));
    }
}