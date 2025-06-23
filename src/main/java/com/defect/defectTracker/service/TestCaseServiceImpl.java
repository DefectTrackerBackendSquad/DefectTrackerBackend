package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.service.TestCaseService;
import jakarta.transaction.Transactional;
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

    @Transactional
    public abstract class testcaseserviceimpl implements testCaseService {

        Logger logger = Logger.getLogger(testcaseserviceimpl.class.getName());

        @Autowired
        private TestCaseRepo testCaseRepository;

        public testcaseserviceimpl(TestCaseRepo testCaseRepository) {
            this.testCaseRepository = testCaseRepository;
        }

        //@Override
        public List<TestCase> getTestCasesByModuleId(Long moduleId) {
            List<TestCase> testCases = testCaseRepository.findByModule_Id(moduleId);
            logger.info(String.valueOf(testCases.size()));
            return testCases;
        }

        @Autowired
        private TestCaseRepo testCaseRepo;

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


        @Override
        public List<TestCaseDto> getTestCasesByProjectId(String projectId) {
            List<TestCase> testCases = testCaseRepository.findByProjectProjectId(projectId);

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
