package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.service.ReleaseTestCaseService;
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
        } catch (Exception e) {
            return new StandardResponse("failure", "Data not found", null, 4000);
        }
    }
}
