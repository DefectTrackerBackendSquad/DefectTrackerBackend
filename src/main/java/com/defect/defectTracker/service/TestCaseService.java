package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;

import java.util.List;

import com.defect.defectTracker.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TestCaseService {
    TestCaseDto deleteByTestCaseId(String testCaseId);
    List<TestCaseDto> getByModuleId(Long moduleId);
    List<TestCaseDto> getBySubModuleId(Long subModuleId);
    boolean testCaseExists(String testCaseId);
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
    List<TestCaseDto> getTestCasesByProjectId(String projectId);
    ResponseEntity<ApiResponse> updateTestCase(String testCaseId, TestCaseDto dto);
}
