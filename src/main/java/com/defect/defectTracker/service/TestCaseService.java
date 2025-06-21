package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;

public interface TestCaseService {
    boolean testCaseExists(String testCaseId);
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
}
