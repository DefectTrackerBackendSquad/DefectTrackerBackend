package com.defect.defectTracker.service;

//import org.springframework.beans.factory.annotation.Autowired;

import com.defect.defectTracker.dto.TestCaseDto;
import org.springframework.stereotype.Service;

import java.util.List;

//import com.defect.defectTracker.dto.TestCaseDto;
@Service
public interface TestCaseService {
    List<TestCaseDto> getTestCasesByProjectId(String projectId);
    boolean testCaseExists(String testCaseId);
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
}