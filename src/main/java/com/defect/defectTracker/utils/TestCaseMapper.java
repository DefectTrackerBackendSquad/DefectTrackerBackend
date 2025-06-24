package com.defect.defectTracker.utils;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.*;

public class TestCaseMapper {

    //  Static method to map DTO fields to TestCase entity
    public static void mapDtoToEntity(TestCaseDto dto, TestCase testCase,
                                      SubModule subModule, Project project,
                                      Severity severity, Type type) {

        testCase.setTestCaseId(dto.getTestCaseId());
        testCase.setDescription(dto.getDescription());
        testCase.setSteps(dto.getSteps());
        testCase.setSubModule(subModule);
        testCase.setProject(project);
        testCase.setSeverity(severity);
        testCase.setType(type);
    }
}
