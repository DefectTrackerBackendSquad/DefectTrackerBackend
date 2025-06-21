package com.defect.defectTracker.exceptionHandler;

public class ReleaseTestCaseNotFoundException extends RuntimeException {
  public ReleaseTestCaseNotFoundException(String message) {
    super(message);
  }
}
