package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/{projectId}")
    public ResponseEntity<Object> getTestCasesByProjectId(
            @RequestHeader(value = "Content-Type", defaultValue = "application/json") String contentType,
            @PathVariable String projectId) {

        try {
            List<TestCaseDto> testCases = testCaseService.getTestCasesByProjectId(projectId);

            if (testCases.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse("failure", 4000, "Data not found"));
            }

            return ResponseEntity.ok()
                    .body(new ApiResponse("success", 2000, "Retrieved Successfully", testCases));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("failure", 4000, "Retrieved failed"));
        }
    }

    // Inner class for API response structure
    private static class ApiResponse {
        private String status;
        private int statusCode;
        private String message;
        private List<TestCaseDto> result;

        // Constructors and getters/setters remain the same as before
        public ApiResponse(String status, int statusCode, String message) {
            this.status = status;
            this.statusCode = statusCode;
            this.message = message;
        }

        public ApiResponse(String status, int statusCode, String message, List<TestCaseDto> result) {
            this.status = status;
            this.statusCode = statusCode;
            this.message = message;
            this.result = result;
        }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public int getStatusCode() { return statusCode; }
        public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public List<TestCaseDto> getResult() { return result; }
        public void setResult(List<TestCaseDto> result) { this.result = result; }
    }
}