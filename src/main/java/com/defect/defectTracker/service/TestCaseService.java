package com.defect.defectTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.defect.defectTracker.entity.TestCase;
import org.springframework.stereotype.Service;
import com.defect.defectTracker.dto.TestCaseDto;
import java.util.List;


@Service
public interface TestCaseService {
    TestCaseDto deleteByTestCaseId(String testCaseId);

    static boolean testCaseExists(String testCaseId) {
        return false;
    }

    static TestCaseDto createTestCase(TestCaseDto dto) {
        return dto;
    }

    List<TestCase> getTestCasesByModuleId(Long moduleId);

    List<TestCaseDto> getTestCasesByProjectId(String projectId);

    List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);

    public interface testCaseService {
        List<TestCaseDto> getTestCasesByProjectId(String projectId);

        List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);

        boolean testCaseExists(String testCaseId);

        TestCaseDto createTestCase(TestCaseDto testCaseDto);
    }
}