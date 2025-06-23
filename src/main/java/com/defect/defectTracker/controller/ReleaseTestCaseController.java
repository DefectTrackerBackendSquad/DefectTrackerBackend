package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/releaseTestCase")
@CrossOrigin
public class ReleaseTestCaseController {

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;

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
