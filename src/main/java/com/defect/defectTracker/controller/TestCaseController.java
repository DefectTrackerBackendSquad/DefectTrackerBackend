package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.response.ApiResponse;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @DeleteMapping("/delete/{testCaseId}")
    public ResponseEntity<?> deleteByTestCaseId(@PathVariable String testCaseId) {
        try {
            testCaseService.deleteByTestCaseId(testCaseId);
            return ResponseEntity.ok(Map.of("message", "Test case deleted successfully."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Internal server error. Unable to delete test case."));
        }
    }

    @GetMapping(value = "/module/{moduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getTestCasesByModuleId(@PathVariable Long moduleId) {
        List<TestCaseDto> testCases = testCaseService.getTestCasesByModuleIdDto(moduleId); // use DTO version

        if (testCases == null || testCases.isEmpty()) {
            logger.warn("No test cases found for module ID: {}", moduleId);
            return ResponseEntity.ok(new StandardResponse("Success", 2000, "No data found", null));
        }

        logger.info("Fetched {} test cases for module ID: {}", testCases.size(), moduleId);
        return ResponseEntity.ok(new StandardResponse("Success", 2000, "Retrieved Successfully", testCases));
    }

    @GetMapping("/{subModuleId}")
    public ResponseEntity<?> getSubModuleId(@PathVariable String subModuleId) {
        List<TestCaseDto> testCases = testCaseService.getTestCasesBySubModuleId(Long.valueOf(subModuleId));
        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new StandardResponse("failure", 4000, "Data not found", null)
            );
        }
        return ResponseEntity.ok(
                new StandardResponse("success", 2000, "Retrieved Successfully!", testCases)
        );
    }

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new StandardResponse("failure", 4000, "Missing required fields", null)
            );
        }

        if (testCaseService.testCaseExists(dto.getTestCaseId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new StandardResponse("failure", 4000, "TestCase already exists", null)
            );
        }

        try {
            TestCaseDto saved = testCaseService.createTestCase(dto);
            if (saved == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new StandardResponse("failure", 4000, "Save Failed", null)
                );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new StandardResponse("success", 2000, "Saved Successfully.", null)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new StandardResponse("failure", 4000, "Save Failed", null)
            );
        }
    }

    @PutMapping("/{testCaseId}")
    public ResponseEntity<ApiResponse> updateTestCase(
            @PathVariable String testCaseId,
            @RequestBody TestCaseDto dto) {
        return testCaseService.updateTestCase(testCaseId, dto);
    }
}
