package com.defect.defectTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releaseTestcases")
public class ReleaseTestCaseController {

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;

    @PostMapping
    public ReleaseTestCaseDto create(@RequestBody ReleaseTestCaseDto dto) {
        return releaseTestCaseService.createReleaseTestCase(dto);
    }
    @GetMapping("/{releaseId}")
    public ResponseEntity<List<ReleaseTestCaseDto>> getTestCasesByReleaseId(@PathVariable String releaseId) {
        List<ReleaseTestCaseDto> testCases = releaseTestCaseService.getTestCasesByReleaseId(releaseId);
        return ResponseEntity.ok(testCases);
    }
}