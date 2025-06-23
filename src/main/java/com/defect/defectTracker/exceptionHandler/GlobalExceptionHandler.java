package com.defect.defectTracker.exceptionHandler;

import com.defect.defectTracker.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<String> customException(HttpMessageNotWritableException e) {
        logger.info("No relevant foreign data there");
        return ResponseEntity.ok("Data Not Found");

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

