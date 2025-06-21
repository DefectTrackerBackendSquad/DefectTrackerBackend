package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/testcases")
@RequiredArgsConstructor
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
                    .body(new ApiResponse("success", 2000, "Retrieved Successfully"));
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

        @Autowired
        private TestCaseImportService importService;

        @PostMapping("/import")
        public ResponseEntity<String> importTestCases(@RequestParam("file") MultipartFile file) {
            try {
                importService.importTestCasesFromCsv(file);
                return ResponseEntity.ok("Success");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to import: " + e.getMessage());
            }
        }
    }
}
