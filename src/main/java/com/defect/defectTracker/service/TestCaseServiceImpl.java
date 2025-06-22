package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
//import com.defect.defectTracker.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseRepo testCaseRepository;

    @Override
    public List<TestCaseDto> getTestCasesByProjectId(String projectId) {
        List<TestCase> testCases = testCaseRepository.findByProjectProjectId(projectId);

        return testCases.stream().map(testCase -> {
            TestCaseDto dto = new TestCaseDto();
            dto.setId(testCase.getId());
            dto.setDescription(testCase.getDescription());
            dto.setSteps(testCase.getSteps());
            dto.setTestCaseId(testCase.getTestCaseId());
            dto.setModuleId(testCase.getModule().getModuleId());
            dto.setProjectId(testCase.getProject().getProjectId());
            dto.setSeverityId(testCase.getSeverity().getId());
            dto.setSubModuleId(testCase.getSubModule().getSubModuleId());
            dto.setTypeId(testCase.getType().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}