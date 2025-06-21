package com.defect.defectTracker.exceptionHandler;

public class ReleaseTestCaseBadRequestException extends RuntimeException {
<<<<<<< HEAD
    private final int code;
    public ReleaseTestCaseBadRequestException(int code, String message) {
        super(message);
        this.code = code;
    }
    public int getCode() { return code; }
}

=======
  public ReleaseTestCaseBadRequestException(String message) {
    super(message);
  }
}
>>>>>>> 9ee051a3817610d70c794a7e500c13dfc5bc9904
