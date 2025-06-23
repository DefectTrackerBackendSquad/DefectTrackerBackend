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
            return ResponseEntity.ok(new StandardResponse("success",  "Test case deleted successfully.", null,2000));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponse("failure", e.getMessage(), null,4000));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("failure",  "Unable to delete test case.", null,4000));
        }
    }

    @GetMapping("module/{moduleId}")
    public ResponseEntity<StandardResponse> getTestCasesByModuleId(@PathVariable Long moduleId) {
        List<TestCase> testCases = testCaseService.getTestCasesByModuleId(moduleId);
        return ResponseEntity.ok(
                new StandardResponse("success",  "Retrieved successfully",testCases,2000)
        );
    }

    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<StandardResponse> getBySubModuleId(@PathVariable Long subModuleId) {
        List<TestCaseDto> testCases = testCaseService.getTestCasesBySubModuleId(subModuleId);
        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure",  "Data not found", null,4000));
        }
        return ResponseEntity.ok(new StandardResponse("success", "Retrieved Successfully!", testCases, 2000));
    }

    @PostMapping("/import")
    public ResponseEntity<StandardResponse> importTestCases(@RequestParam("file") MultipartFile file) {
        try {
            importService.importTestCasesFromCsv(file);
            return ResponseEntity.ok(new StandardResponse("success", "Import Successful", null, 2000));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("failure", "Failed to import: " + e.getMessage(), null, 5000));
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
                    .body(new StandardResponse("failure", "Missing required fields", null, 4000));
        }

        if (testCaseService.testCaseExists(dto.getTestCaseId())) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", "TestCase already exists", null, 4000));
        }

        try {
            TestCaseDto saved = testCaseService.createTestCase(dto);
            if (saved == null) {
                return ResponseEntity.badRequest()
                        .body(new StandardResponse("failure", "Save Failed", null, 4000));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StandardResponse("success", "Saved Successfully.", saved, 2000));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", "Save Failed", null, 4000));
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
                        .body(new StandardResponse("failure", "Data not found", null, 4000));
            }

            return ResponseEntity.ok()
                    .body(new StandardResponse("success", "Retrieved Successfully", testCases, 2000));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure", "Retrieve failed", null, 4000));
        }
    }
}
