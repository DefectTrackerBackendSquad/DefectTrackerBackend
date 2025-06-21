package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/release-testcases")
public class ReleaseTestCaseController {

    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;

    @PostMapping
    public ReleaseTestCaseDto create(@RequestBody ReleaseTestCaseDto dto) {
        return releaseTestCaseService.createReleaseTestCase(dto);
    }
}

