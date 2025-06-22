package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseImportService;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class TestCaseController {

    @Autowired
    private TestCaseService testcaseService;
    private TestCaseImportService importService;


    @GetMapping("/{subModuleId}")
    public ResponseEntity<?> getSubModuleId(@PathVariable Long subModuleId) {
        List<TestCaseDto> testCases = testcaseService.getTestCasesBySubModuleId(subModuleId);
        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new StandardResponse("failure", "4000", "Data not found", null)
            );
        }
        return ResponseEntity.ok().body(
                new StandardResponse("success", "2000", "Retrieved Successfully.!", testCases)
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
                    new StandardResponse("failure", "4000", "Missing required fields", null)
            );
        }

        if (testcaseService.testCaseExists(dto.getTestCaseId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new StandardResponse("failure", "4000", "TestCase already exists", null)
            );
        }

        try {
            TestCaseDto saved = testcaseService.createTestCase(dto);
            if (saved == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new StandardResponse("failure", "4000", "Save Failed", null)
                );
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new StandardResponse("success", "2000", "Saved Successfully.", null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new StandardResponse("failure", "4000", "Save Failed", null)
            );
        }
    }
}