// src/main/java/com/example/defectTracker/exceptions/ResourceNotFoundException.java
package com.defect.defectTracker.exceptionHandler;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}