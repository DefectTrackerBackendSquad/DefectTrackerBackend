package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.TestCase;

import java.util.List;

public interface TestCaseService {
    List<TestCase> getTestCasesByModuleId(Long moduleId);
}
