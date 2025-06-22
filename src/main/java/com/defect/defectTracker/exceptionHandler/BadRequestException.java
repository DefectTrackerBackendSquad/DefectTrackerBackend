// src/main/java/com/example/defectTracker/exceptions/BadRequestException.java
package com.defect.defectTracker.exceptionHandler;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}