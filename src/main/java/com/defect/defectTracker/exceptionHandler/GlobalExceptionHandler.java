package com.defect.defectTracker.exceptionHandler;

import com.defect.defectTracker.response.ApiResponse;
import com.defect.defectTracker.response.ApiResponseCodes;
import com.defect.defectTracker.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFound(ResourceNotFoundException ex) {
        ApiResponse response = new ApiResponse(
                "Failure",
                ApiResponseCodes.ERROR_DATA_NOT_FOUND,
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneral(Exception ex) {
        ApiResponse response = new ApiResponse(
                "Failure",
                ApiResponseCodes.ERROR_UPDATE_FAILED,
                ApiResponseCodes.MSG_UPDATE_FAILED
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
