package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.entity.TestCase;


import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    @Autowired
    private TestCaseRepo testCaseRepo;

    @Override
    public List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId) {
        List<TestCase> testCases = testCaseRepo.findBySubModule_Id(subModuleId);
        return testCases.stream().map(testCase -> {
            TestCaseDto dto = new TestCaseDto();
            dto.setId(testCase.getId());
            dto.setBriefDescription(testCase.getDescription());
            dto.setSubModuleId(testCase.getSubModule() != null ? String.valueOf(testCase.getSubModule().getId()) : null);
            dto.setSeverityId(testCase.getSeverity() != null ? testCase.getSeverity().getId() : null);
            dto.setSteps(testCase.getSteps());
            dto.setTypeId(testCase.getType() != null ? testCase.getType().getId() : null);
            dto.setModuleId(testCase.getModules() != null ? String.valueOf(testCase.getModules().getId()) : null);
            dto.setProject(testCase.getProject() != null ? testCase.getProject().getProjectName() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}
