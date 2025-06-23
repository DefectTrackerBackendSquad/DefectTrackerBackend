package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.utils.StandardResponse;

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
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private TestCaseImportService importService;

    private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    // Get test cases by module ID
    @GetMapping(value = "/module/{moduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getTestCasesByModuleId(@PathVariable Long moduleId) {
        List<TestCase> testCases = testCaseService.getTestCasesByModuleId(moduleId);

        if (testCases == null || testCases.isEmpty()) {
            logger.warn("No test cases found for module ID: {}", moduleId);
            return ResponseEntity.ok(new StandardResponse("Success", "Retrieved Successfully", null, 2000));
        }

        logger.info("Fetched {} test cases for module ID: {}", testCases.size(), moduleId);
        return ResponseEntity.ok(new StandardResponse("Success", "Retrieved Successfully", testCases.toString(), 2000));
    }

    // Get test cases by project ID
    @GetMapping("/project/{projectId}")
    public ResponseEntity<StandardResponse> getTestCasesByProjectId(@PathVariable String projectId) {
        try {
            List<TestCaseDto> testCases = testCaseService.getTestCasesByProjectId(projectId);

            if (testCases.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new StandardResponse("failure", "4000", "Data not found", null));
            }

            return ResponseEntity.ok(new StandardResponse("success", "2000", "Retrieved Successfully", testCases));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("failure", "4000", "Retrieval failed", null));
        }
    }

    // Get test cases by submodule ID
    @GetMapping("/submodule/{subModuleId}")
    public ResponseEntity<StandardResponse> getTestCasesBySubModuleId(@PathVariable Long subModuleId) {
        List<TestCaseDto> testCases = testCaseService.getTestCasesBySubModuleId(subModuleId);

        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", "4000", "Data not found", null));
        }

        return ResponseEntity.ok(new StandardResponse("success", "2000", "Retrieved Successfully!", testCases));
    }

    // Import test cases from CSV
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

    // Create test case
    @PostMapping
    public ResponseEntity<StandardResponse> createTestCase(@RequestBody TestCaseDto dto) throws InterruptedException {
        if (dto == null ||
                dto.getTestCaseId() == null || dto.getTestCaseId().trim().isEmpty() ||
                dto.getSubModuleId() == null ||
                dto.getDescription() == null || dto.getDescription().trim().isEmpty() ||
                dto.getSeverityId() == null ||
                dto.getModuleId() == null ||
                dto.getSteps() == null || dto.getSteps().trim().isEmpty() ||
                dto.getTypeId() == null ||
                dto.getProjectId() == null) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure", "4000", "Missing required fields", null));
        }

        if (TestCaseService.testCaseExists(dto.getTestCaseId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("failure", "4000", "TestCase already exists", null));
        }

        try {
            TestCaseDto saved = TestCaseService.createTestCase(dto);
            if (saved == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new StandardResponse("failure", "4000", "Save Failed", null));
            }
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new StandardResponse("success", "2000", "Saved Successfully", saved));
        } catch (Exception e) {
            logger.error("Error saving test case", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("failure", "4000", "Save Failed", null));
        }
    }



    @DeleteMapping("/delete/{testCaseId}")
    public ResponseEntity<?> deleteByTestCaseId(@PathVariable String testCaseId) {
        try {
            testCaseService.deleteByTestCaseId(testCaseId); // Just call service, don't return dto

            return ResponseEntity.ok(Map.of("message", "Test case deleted successfully."));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace(); // Optional: useful during debugging
            return ResponseEntity.status(500).body(Map.of("error", "Internal server error. Unable to delete test case."));
        }
    }
}
