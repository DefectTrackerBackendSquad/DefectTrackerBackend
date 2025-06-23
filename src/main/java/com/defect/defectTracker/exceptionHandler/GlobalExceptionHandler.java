package com.defect.defectTracker.exceptionHandler;

import com.defect.defectTracker.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler extends RuntimeException {
    public GlobalExceptionHandler(String message) {
        super(message);
    }
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