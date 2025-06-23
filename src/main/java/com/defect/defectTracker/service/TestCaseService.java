package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;

import com.defect.defectTracker.entity.TestCase;

import java.util.List;
import com.defect.defectTracker.dto.TestCaseDto;
import java.util.List;

public interface TestCaseService {
    TestCaseDto deleteByTestCaseId(String testCaseId);
    List<TestCase> getTestCasesByModuleId(Long moduleId);
    List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);
    boolean testCaseExists(String testCaseId);
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
    List<TestCaseDto> getTestCasesByProjectId(String projectId);
}
