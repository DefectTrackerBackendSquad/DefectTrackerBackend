package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/release-testcases")

@CrossOrigin
public class ReleaseTestCaseController {

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;


    @PostMapping
    public ResponseEntity<StandardResponse> createReleaseTestCase(@RequestBody ReleaseTestCaseDto dto) {
        try {
            ReleaseTestCase created = releaseTestCaseService.createReleaseTestCase(dto);
            return ResponseEntity.ok(new StandardResponse("success", "Release Test Case Created Successfully", created, 2000));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new StandardResponse("failure", "Error creating release test case", null, 5000));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateReleaseTestCase(@PathVariable Long id, @RequestBody ReleaseTestCaseDto dto) {
        try {
            releaseTestCaseService.updateReleaseTestCase(id, dto);
            return ResponseEntity.ok(new StandardResponse("success", "Updated Successfully", null, 2000));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new StandardResponse("failure", "Error updating release test case", null, 5000));
        }
    }
}

    @GetMapping("/{releaseTestCaseId}")
    public StandardResponse getTestCase(@PathVariable String releaseTestCaseId) {
        try {
            TestCaseResponseDTO dto = releaseTestCaseService.getTestCaseByReleaseTestCaseId(releaseTestCaseId);
            return new StandardResponse("success", "Test case retrieved successfully", dto, 200);
        } catch (ResourceNotFoundException e) {
            return new StandardResponse("failure", e.getMessage(), null, 4000);
        } catch (Exception e) {
            return new StandardResponse("failure", "Unexpected error occurred", null, 5000);
        }
    }

    @DeleteMapping("/{releaseTestCaseId}")
    public StandardResponse deleteTestCase(@PathVariable String releaseTestCaseId) {
        try {
            releaseTestCaseService.deleteTestCaseByReleaseTestCaseId(releaseTestCaseId);
            return new StandardResponse("success", "Test case deleted successfully", null, 200);
        } catch (ResourceNotFoundException e) {
            return new StandardResponse("failure", e.getMessage(), null, 4000);
        } catch (Exception e) {
            return new StandardResponse("failure", "Unexpected error occurred", null, 5000);
        }
    }
}

