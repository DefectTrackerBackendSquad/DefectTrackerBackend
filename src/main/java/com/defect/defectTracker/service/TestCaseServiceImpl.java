package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.service.TestCaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Contract;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Data
@Service
public class TestCaseServiceImpl implements TestCaseService {
    @Override
    public TestCaseDto deleteByTestCaseId(String testCaseId) {
        return null;
    }

    @Override
    public List<TestCase> getTestCasesByModuleId(Long moduleId) {
        return List.of();
    }

    @Override
    public List<TestCaseDto> getTestCasesByProjectId(String projectId) {
        return List.of();
    }

    @Override
    public List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId) {
        return List.of();
    }

        Logger logger = Logger.getLogger(TestCaseServiceImpl.class.getName());

        @Autowired
        private TestCaseRepo testCaseRepo;

        public testcaseserviceimpl(TestCaseRepo testCaseRepo) {
            this.testCaseRepo = testCaseRepo;
        }




        @Contract
        @Override
        public List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId) {
            List<TestCase> testCases = testCaseRepo.findBySubModule_Id(subModuleId);
            return testCases.stream().map(testCase -> {
                TestCaseDto dto = new TestCaseDto();
                dto.setId(testCase.getId());
                dto.setDescription(testCase.getDescription());
                //dto.setSubModuleId(testCase.getSubModule() != null ? String.valueOf((Object) testCase.getSubModule().getId()) : null);
                dto.setSeverityId(testCase.getSeverity() != null ? testCase.getSeverity().getId() : null);
                dto.setSteps(testCase.getSteps());
                dto.setTypeId(testCase.getType() != null ? testCase.getType().getId() : null);
               // dto.setModuleId(testCase.getModules() != null ? String.valueOf(testCase.getModules().getId()) : null);
                dto.setProjectId(testCase.getProject() != null ? testCase.getProject().getId() : null);
                dto.setTestCaseId(testCase.getTestCaseId());
                return dto;
            }).collect(Collectors.toList());
        }

        @Autowired
        private SubModuleRepo subModuleRepo;

        @Autowired
        private ModuleRepo moduleRepo;

        @Autowired
        private ProjectRepo projectRepo;

        @Autowired
        private SeverityRepo severityRepo;

    @Autowired
    private TestCaseRepo repository;


    @Override
    public TestCaseDto deleteByTestCaseId(String testCaseId) {
        Optional<TestCase> optional = repository.findByTestCaseId(testCaseId);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Test case with ID " + testCaseId + " not found");
        }

        TestCase testCase = optional.get();

        TestCaseDto dto = new TestCaseDto(
                testCase.getId(),
                testCase.getTestCaseId(),
                testCase.getDescription(),
                testCase.getSteps(),
                testCase.getModules().getId(),
                testCase.getProject().getId(),
                testCase.getSeverity().getId(),
                testCase.getSubModule().getId(),
                testCase.getType().getId()
        );

        repository.deleteByTestCaseId(testCaseId); // Deletes without lazy loading issues
        return dto;
    }
    private TypeRepo typeRepo;

        @Override
        public List<TestCaseDto> getTestCasesByProjectId(String projectId) {
            List<TestCase> testCases = testCaseRepo.findByProjectProjectId(projectId);

            return testCases.stream().map(testCase -> {
                TestCaseDto dto = new TestCaseDto();
                dto.setId(testCase.getId());
                dto.setDescription(testCase.getDescription());
                dto.setSteps(testCase.getSteps());
                dto.setTestCaseId(testCase.getTestCaseId());
                dto.setModuleId(Long.valueOf(testCase.getModule().getModuleId()));
                dto.setProjectId(testCase.getProject() != null ? testCase.getProject().getId() : null);
                dto.setSeverityId(testCase.getSeverity().getId());
                dto.setSubModuleId(Long.valueOf(testCase.getSubModule().getSubModuleId()));
                dto.setTypeId(testCase.getType().getId());
                return dto;
            }).collect(Collectors.toList());
        }

        private void validateTestCaseDto(TestCaseDto dto) {
        // description: string only, max 255
        if (dto.getDescription() == null || dto.getDescription().length() > 255 || !dto.getDescription().matches("^[\\p{L}0-9 .,'\"!?-]*$")) {
            throw new IllegalArgumentException("Description must be a string with max 255 characters.");
        }

        // steps: string only, max 255
        if (dto.getSteps() == null || dto.getSteps().length() > 255 || !dto.getSteps().matches("^[\\p{L}0-9 .,'\"!?-]*$")) {
            throw new IllegalArgumentException("Steps must be a string with max 255 characters.");
        }
        // projectId: only check for null since it's a Long
        if (dto.getProjectId() == null) {
            throw new IllegalArgumentException("projectId must not be null.");
        }
        }
    }
}
