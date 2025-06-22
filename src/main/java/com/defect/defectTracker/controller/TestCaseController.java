package com.defect.defectTracker.controller;

//import com.defect.defectTracker.service.TestCaseService;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.List;

@Service
@RestController
@RequestMapping("/api/v1/testcase")
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
        //private TestCaseImportService importService;

    private  TestCaseImportService importService;
    private  TestCaseService testCaseService;


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



    @PostMapping
    public ResponseEntity<?> createTestCase(@RequestBody TestCaseDto dto) {
        if (dto == null ||
                dto.getTestCaseId() == null || dto.getTestCaseId().trim().isEmpty() ||
                dto.getSubModuleId() == null || dto.getSubModuleId().trim().isEmpty() ||
                dto.getDescription() == null || dto.getDescription().trim().isEmpty() ||
                dto.getSeverityId() == null ||
                dto.getModuleId() == null || dto.getModuleId().trim().isEmpty() ||
                dto.getSteps() == null || dto.getSteps().trim().isEmpty() ||
                dto.getTypeId() == null ||
                dto.getProjectId() == null || dto.getProjectId().trim().isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "failure");
            response.put("statusCode", 4000);
            response.put("message", "Missing required fields");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (testCaseService.testCaseExists(dto.getTestCaseId())) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "failure");
            response.put("statusCode", 4000);
            response.put("message", "TestCase already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            TestCaseDto saved = testCaseService.createTestCase(dto);
            if (saved == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "failure");
                response.put("statusCode", 4000);
                response.put("message", "Save Failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("statusCode", 2000);
            response.put("message", "Saved Successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "failure");
            response.put("statusCode", 4000);
            response.put("message", "Save Failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
