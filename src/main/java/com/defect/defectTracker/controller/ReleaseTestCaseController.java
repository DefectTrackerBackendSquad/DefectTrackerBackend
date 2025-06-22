package com.defect.defectTracker.controller;

import com.defect.defectTracker.service.ReleaseTestCaseService;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import com.defect.defectTracker.utils.ErrorResponse;

@RestController
@RequestMapping("/api/v1/release-testcase")
public class ReleaseTestCaseController {
@Autowired
    private ReleaseTestCaseService releaseTestCaseService;

    public ReleaseTestCaseController(ReleaseTestCaseService releaseTestCaseService) {
        this.releaseTestCaseService = releaseTestCaseService;
    }

    @DeleteMapping("/{releaseTestCaseId}")
    public ResponseEntity<ErrorResponse> deleteReleaseTestCase(@PathVariable String releaseTestCaseId) {
        try {
            String response = releaseTestCaseService.deleteReleaseTestCase(releaseTestCaseId).toString();
            // Success case (200 OK with success status)
            ErrorResponse successResponse = new ErrorResponse(
                    "success",
                    "10003",  // Custom success code
                    "Release test case deleted successfully."
            );
            return ResponseEntity.ok(successResponse);
        } catch (ResourceNotFoundException e) {
            // Treat "not found" as a successful operation (200 OK with failure status)
            ErrorResponse errorResponse = new ErrorResponse(
                    "failure",
                    "10009",
                    "Release test case not found with the provided ID."
            );
            return ResponseEntity.ok(errorResponse); // Still HTTP 200, but with "failure" in body

        }
        catch (Exception e) {
            // Handle other exceptions and return a generic error response
            ErrorResponse errorResponse = new ErrorResponse(
                    "failure",
                    "10004",  // Custom error code
                    "An unexpected error occurred: " + e.getMessage()
            );
            return ResponseEntity.ok(errorResponse);
        }
    }
}