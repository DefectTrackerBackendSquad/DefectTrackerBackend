package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService service;

    @DeleteMapping("/delete/{testCaseId}")
    public ResponseEntity<?> deleteByTestCaseId(@PathVariable String testCaseId) {
        try {
            service.deleteByTestCaseId(testCaseId); // Just call service, don't return dto

            return ResponseEntity.ok(Map.of("message", "Test case deleted successfully."));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace(); // Optional: useful during debugging
            return ResponseEntity.status(500).body(Map.of("error", "Internal server error. Unable to delete test case."));
        }
    }

}
