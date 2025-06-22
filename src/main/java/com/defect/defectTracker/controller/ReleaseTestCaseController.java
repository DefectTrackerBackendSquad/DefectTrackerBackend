package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/release-test-cases")
public class ReleaseTestCaseController {
    @Autowired
    private ReleaseTestCaseService releaseTestCaseService;

    @PostMapping
    public ResponseEntity<ReleaseTestCase> createReleaseTestCase(@RequestBody ReleaseTestCaseDto dto) {
        ReleaseTestCase created = releaseTestCaseService.createReleaseTestCase(dto);
        return ResponseEntity.ok(created);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateReleaseTestCase(@PathVariable Long id, @RequestBody ReleaseTestCaseDto dto) {
        releaseTestCaseService.updateReleaseTestCase(id, dto);

        return ResponseEntity.ok(

                new java.util.HashMap<String, Object>() {{
                    git commitb
                    put("status", "success");
                    put("statusCode", 2000);
                    put("message", "Updated Successfully");
                }}
        );
    }


}
