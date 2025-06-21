package com.defect.defectTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/releases")
@RequiredArgsConstructor



public class ReleaseTestCaseController {
    private final ReleaseTestCaseService releaseService;


    @GetMapping("/{releaseId}")
    public ResponseEntity<List<ReleaseTestCaseDto>> getTestCasesByReleaseId(@PathVariable String releaseId) {
        List<ReleaseTestCaseDto> testCases = releaseService.getTestCasesByReleaseId(releaseId);
        return ResponseEntity.ok(testCases);
    }
}
