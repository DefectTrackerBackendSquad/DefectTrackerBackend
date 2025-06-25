package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import com.defect.defectTracker.util.StandardResponse;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/releaseTestCase")
public class ReleaseTestCaseController {
    Logger logger = LoggerFactory.getLogger(ReleaseTestCaseController.class);

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;

    @PostMapping
    public ResponseEntity<StandardResponse> allocateTestCaseRelease(@RequestBody ReleaseTestCaseDto releaseTestCaseDto) {
        logger.info("Start Post");
        ReleaseTestCase releaseTestCase = releaseTestCaseService.allocateTestCaseRelease(releaseTestCaseDto);

        logger.info(String.valueOf(releaseTestCaseDto));
        if (releaseTestCaseDto == null || releaseTestCaseDto.getTestDate() == null || releaseTestCaseDto.getTestTime() == null ||
                releaseTestCaseDto.getTestCaseStatus() == null || releaseTestCaseDto.getReleaseId() == null ||
                releaseTestCaseDto.getTestCaseId() == null) {
            return ResponseEntity.badRequest()
                    .body(new StandardResponse("failure", "Missing required fields", null, 4000));
        }
        if (releaseTestCase != null) {
            return ResponseEntity.ok(new StandardResponse("Success", "Allocated successfully", null, 2000));
        } else {
            return ResponseEntity.ok(new StandardResponse("Failure", "Allocation failed", null, 4000));
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
        logger.info("Start Delete");
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
