package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;

import java.util.List;
import com.defect.defectTracker.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface TestCaseService {
    TestCaseDto deleteByTestCaseId(String testCaseId);
    List<TestCaseDto> getTestCasesByModuleIdDto(Long moduleId);
    List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);
    boolean testCaseExists(String testCaseId);
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
    ResponseEntity<ApiResponse> updateTestCase(String testCaseId, TestCaseDto dto);

}
