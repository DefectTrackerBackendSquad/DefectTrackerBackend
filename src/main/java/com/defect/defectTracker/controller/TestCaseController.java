package com.defect.defectTracker.controller;

import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.service.TestCaseService;
import com.defect.defectTracker.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/testcase")
public class TestCaseController {

    Logger logger = LoggerFactory.getLogger(TestCaseController.class);

    @Autowired
    private TestCaseService testCaseService;

    @GetMapping(value= "/module/{moduleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StandardResponse> getTestCasesByModuleId(@PathVariable Long moduleId) {
        List<TestCase> testCases = testCaseService.getTestCasesByModuleId(moduleId);

        StandardResponse standardResponse = new StandardResponse("Success","Retrived Successfully",null,2000);

        logger.info(standardResponse.getMessage());
        if (testCases == null || testCases.isEmpty()) {
            logger.warn("No test cases found for module ID: {}", moduleId);
            return ResponseEntity.ok(new StandardResponse("Success","Retrived Successfully",null,2000));
        }

        logger.info("Fetched {} test cases for module ID: {}", testCases.size(), moduleId);
        return ResponseEntity.ok(new StandardResponse("Success","Retrived Successfully",testCases,2000));
    }
}