package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.response.ApiResponse;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.util.StandardResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/testcase")
@CrossOrigin
public class TestCaseController {

    Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestCaseImportService importService;

    @PostMapping
    public ResponseEntity<StandardResponse> createTestCase(@RequestBody TestCaseDto dto) {

        logger.info("Start Testcase Post");
        if (dto == null || dto.getSubModuleId() == null || dto.getDescription() == null ||
                dto.getSeverityId() == null || dto.getModuleId() == null ||
                dto.getSteps() == null || dto.getTypeId() == null || dto.getProjectId() == null) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", "Missing required fields", null, 4000));
        }
        try {
            TestCaseDto saved = testCaseService.createTestCase(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StandardResponse("success", "Saved Successfully.", saved, 2000));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", "Save Failed: " + e.getMessage(), null, 4000));
        }
    }

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

    @GetMapping("/module/{modulesId}")
    public ResponseEntity<StandardResponse> getByModuleId(@PathVariable Long modulesId) {
        logger.info("Came to module controller gtest case");
        List<TestCaseDto> testCases = testCaseService.getByModuleId(modulesId);
        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure",  "Data not found", null,4000));
        }
        return ResponseEntity.ok(new StandardResponse("success", "Retrieved Successfully!", testCases, 2000));
    }

    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<StandardResponse> getBySubModuleId(@PathVariable Long subModuleId) {
        logger.info("Came to module controller gtest case");
        List<TestCaseDto> testCases = testCaseService.getBySubModuleId(subModuleId);
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



    @GetMapping("/project/{projectId}")
    public ResponseEntity<StandardResponse> getTestCasesByProjectId(
            @RequestHeader(value = "Content-Type", defaultValue = "application/json") String contentType,
            @PathVariable String projectId) {
        logger.info("Came to project controller case");
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

    @PutMapping("/{testCaseId}")
    public ResponseEntity<ApiResponse> updateTestCase(
            @PathVariable String testCaseId,
            @RequestBody TestCaseDto dto) {
        logger.info("Started update");
        return testCaseService.updateTestCase(testCaseId, dto);
    }

}
