package com.defect.defectTracker.exceptionHandler;

public class ReleaseTestCaseBadRequestException extends RuntimeException {
    private final int code;
    public ReleaseTestCaseBadRequestException(int code, String message) {
        super(message);
        this.code = code;
    }
    public int getCode() { return code; }
}

