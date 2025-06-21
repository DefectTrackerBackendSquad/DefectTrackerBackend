package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/testcases")
@CrossOrigin
public class TestCaseController {

    @Autowired
    private TestCaseService testcaseService;


    @GetMapping("/{subModuleId}")
    public ResponseEntity<?> getSubModuleId(@PathVariable Long subModuleId) {
        List<TestCaseDto> testCases = testcaseService.getTestCasesBySubModuleId(subModuleId);
        if (testCases == null || testCases.isEmpty()) {
            return ResponseEntity.badRequest().body(
                new StandardResponse("failure", "4000", "Data not found", null)
            );
        }
        return ResponseEntity.ok().body(
            new StandardResponse("success", "2000", "Retrieved Successfully.!", testCases)
        );
    }
}
