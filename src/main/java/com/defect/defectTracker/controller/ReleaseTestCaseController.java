package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/release-testCase")
@CrossOrigin
public class ReleaseTestCaseController {

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;

    @GetMapping("/{releaseTestCaseId}")
    public StandardResponse getTestCase(@PathVariable String releaseTestCaseId) {
        try {
            TestCaseResponseDTO dto = releaseTestCaseService.getTestCaseByReleaseTestCaseId(releaseTestCaseId);
            return new StandardResponse("success", "Test case retrieved successfully", dto, 200);
        } catch (Exception e) {
            return new StandardResponse("failure", "Data not found", null, 4000);
        }
    }
  
    @DeleteMapping("/{releaseTestCaseId}")
    public ResponseEntity<StandardResponse> deleteReleaseTestCase(@PathVariable String releaseTestCaseId) {
        try {
            String response = releaseTestCaseService.deleteReleaseTestCase(releaseTestCaseId).toString();

            StandardResponse successResponse = new StandardResponse("success", "Release test case deleted successfully.", null, 200);
            return ResponseEntity.ok(successResponse);

        } catch (ResourceNotFoundException e) {
            StandardResponse notFoundResponse = new StandardResponse("failure", "Release test case not found with the provided ID.", null, 404);
            return ResponseEntity.status(404).body(notFoundResponse);

        } catch (Exception e) {
            StandardResponse errorResponse = new StandardResponse("failure", "An unexpected error occurred: " + e.getMessage(), null, 500);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}

