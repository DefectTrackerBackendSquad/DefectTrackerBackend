package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;

public interface TestCaseService {
    TestCaseDto deleteByTestCaseId(String testCaseId);
}
