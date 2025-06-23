package com.defect.defectTracker.service;



//import org.springframework.beans.factory.annotation.Autowired;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestCaseService {
    static boolean testCaseExists(String testCaseId) {
        return false;
    }

    static TestCaseDto createTestCase(TestCaseDto dto) {
        return dto;
    }
//    static boolean testCaseExists(String testCaseId) {
//    }
//
//    static TestCaseDto createTestCase(TestCaseDto dto) {
//    }

    List<TestCase> getTestCasesByModuleId(Long moduleId);

    List<TestCaseDto> getTestCasesByProjectId(String projectId);

    List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);

//import com.defect.defectTracker.dto.TestCaseDto;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//import java.util.List;

    //import com.defect.defectTracker.dto.TestCaseDto;

    public interface testCaseService {
        List<TestCaseDto> getTestCasesByProjectId(String projectId);

        List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId);

        boolean testCaseExists(String testCaseId);

        TestCaseDto createTestCase(TestCaseDto testCaseDto);
    }
}