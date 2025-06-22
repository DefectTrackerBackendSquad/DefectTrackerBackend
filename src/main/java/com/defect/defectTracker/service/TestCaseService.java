package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.TestCase;

import java.util.List;

public interface TestCaseService {
    List<TestCase> getTestCasesByModuleId(Long moduleId);
  
import com.defect.defectTracker.dto.TestCaseDto;
import java.util.List;

public interface TestCaseService {
    List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);
    boolean testCaseExists(String testCaseId);
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
}
