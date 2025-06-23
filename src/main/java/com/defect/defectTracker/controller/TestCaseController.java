package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.utils.StandardResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/testcase")
@CrossOrigin
@RequiredArgsConstructor
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;
    private TestCaseImportService importService;

    @DeleteMapping("/{testCaseId}")
    public ResponseEntity<StandardResponse> deleteByTestCaseId(@PathVariable String testCaseId) {
        try {
            testCaseService.deleteByTestCaseId(testCaseId);
            return ResponseEntity.ok(new StandardResponse("success", 2000, "Test case deleted successfully.", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponse("failure", 4004, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("failure", 5000, "Internal server error. Unable to delete test case.", null));
        }
    }

    @GetMapping("module/{moduleId}")
    public ResponseEntity<StandardResponse> getTestCasesByModuleId(@PathVariable Long moduleId) {
        List<TestCase> testCases = testCaseService.getTestCasesByModuleId(moduleId);
        return ResponseEntity.ok(
                new StandardResponse("success", 2000, "Retrieved successfully",
                        (testCases == null || testCases.isEmpty()) ? null : testCases)
        );
    }

    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<StandardResponse> getBySubModuleId(@PathVariable Long subModuleId) {
        List<TestCaseDto> testCases = testCaseService.getTestCasesBySubModuleId(subModuleId);
        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure", 4000, "Data not found", null));
        }
        return ResponseEntity.ok(new StandardResponse("success", 2000, "Retrieved Successfully!", testCases));
    }

    @PostMapping("/import")
    public ResponseEntity<StandardResponse> importTestCases(@RequestParam("file") MultipartFile file) {
        try {
            importService.importTestCasesFromCsv(file);
            return ResponseEntity.ok(new StandardResponse("success", 2000, "Import Successful", null));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("failure", 5000, "Failed to import: " + e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createTestCase(@RequestBody TestCaseDto dto) {
        if (dto == null ||
                dto.getTestCaseId() == null || dto.getTestCaseId().trim().isEmpty() ||
                dto.getSubModuleId() == null || dto.getSubModuleId().trim().isEmpty() ||
                dto.getDescription() == null || dto.getDescription().trim().isEmpty() ||
                dto.getSeverityId() == null ||
                dto.getModuleId() == null || dto.getModuleId().trim().isEmpty() ||
                dto.getSteps() == null || dto.getSteps().trim().isEmpty() ||
                dto.getTypeId() == null ||
                dto.getProjectId() == null || dto.getProjectId().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", 4000, "Missing required fields", null));
        }

        if (testCaseService.testCaseExists(dto.getTestCaseId())) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", 4000, "TestCase already exists", null));
        }

        try {
            TestCaseDto saved = testCaseService.createTestCase(dto);
            if (saved == null) {
                return ResponseEntity.badRequest()
                        .body(new StandardResponse("failure", 4000, "Save Failed", null));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StandardResponse("success", 2000, "Saved Successfully.", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", 4000, "Save Failed", null));
        }
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<StandardResponse> getTestCasesByProjectId(
            @RequestHeader(value = "Content-Type", defaultValue = "application/json") String contentType,
            @PathVariable String projectId) {
        try {
            List<TestCaseDto> testCases = testCaseService.getTestCasesByProjectId(projectId);

            if (testCases.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new StandardResponse("failure", 4000, "Data not found", null));
            }

            return ResponseEntity.ok()
                    .body(new StandardResponse("success", 2000, "Retrieved Successfully", testCases));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure", 4000, "Retrieve failed", null));
        }
    }
}
