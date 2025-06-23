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
import com.defect.defectTracker.entity.TestCase;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Data
@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {
  
    Logger logger = Logger.getLogger(TestCaseServiceImpl.class.getName());

    @Autowired
    private TestCaseRepo testCaseRepository;
    @Override
    public List<TestCase> getTestCasesByModuleId(Long moduleId) {
        List<TestCase> testCases =  testCaseRepository.findByModules_Id(moduleId);
        logger.info(String.valueOf(testCases.size()));
        return testCases;
    }
  
    @Autowired
    private TestCaseRepo testCaseRepo;

    @Override
    public List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId) {
        List<TestCase> testCases = testCaseRepo.findBySubModule_Id(subModuleId);
        return testCases.stream().map(testCase -> {
            TestCaseDto dto = new TestCaseDto();
            dto.setId(testCase.getId());
            dto.setDescription(testCase.getDescription());
            dto.setSubModuleId(testCase.getSubModule() != null ? String.valueOf(testCase.getSubModule().getId()) : null);
            dto.setSeverityId(testCase.getSeverity() != null ? testCase.getSeverity().getId() : null);
            dto.setSteps(testCase.getSteps());
            dto.setTypeId(testCase.getType() != null ? testCase.getType().getId() : null);
            dto.setModuleId(testCase.getModules() != null ? String.valueOf(testCase.getModules().getId()) : null);
            dto.setProjectId(testCase.getProject() != null ? String.valueOf(testCase.getProject().getId()) : null);
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

        TestCaseDto dto = new TestCaseDto();

        repository.deleteByTestCaseId(testCaseId); // Deletes without lazy loading issues
        return dto;
    }
    private TypeRepo typeRepo;

    @Override
    public boolean testCaseExists(String testCaseId) {
        return testCaseRepo.existsByTestCaseId(testCaseId);
    }

    @Override
    public TestCaseDto createTestCase(TestCaseDto dto) {
        validateTestCaseDto(dto);
        TestCase testCase = new TestCase();
        testCase.setTestCaseId(dto.getTestCaseId());
        testCase.setDescription(dto.getDescription());
        testCase.setSteps(dto.getSteps());

        if (dto.getSubModuleId() != null) {
            testCase.setSubModule(subModuleRepo.findBySubModuleId(dto.getSubModuleId()).orElse(null));
        }
        if (dto.getModuleId() != null) {
            testCase.setModules(moduleRepo.findByModuleId(dto.getModuleId()).orElse(null));
        }
        if (dto.getProjectId() != null) {
            testCase.setProject(projectRepo.findByProjectId(dto.getProjectId()).orElse(null));
        }
        if (dto.getSeverityId() != null) {
            testCase.setSeverity(severityRepo.findById(dto.getSeverityId()).orElse(null));
        }
        if (dto.getTypeId() != null) {
            testCase.setType(typeRepo.findById(dto.getTypeId()).orElse(null));
        }

        TestCase saved = testCaseRepo.save(testCase);
        return mapToDto(saved);
    }

    private TestCaseDto mapToDto(TestCase testCase) {
        TestCaseDto dto = new TestCaseDto();
        dto.setTestCaseId(testCase.getTestCaseId());
        dto.setDescription(testCase.getDescription());
        dto.setSteps(testCase.getSteps());
        dto.setSubModuleId(testCase.getSubModule() != null ? testCase.getSubModule().getSubModuleId() : null);
        dto.setModuleId(testCase.getModules() != null ? testCase.getModules().getModuleId() : null);
        dto.setProjectId(testCase.getProject() != null ? testCase.getProject().getProjectId() : null);
        dto.setSeverityId(testCase.getSeverity() != null ? testCase.getSeverity().getId() : null);
        dto.setTypeId(testCase.getType() != null ? testCase.getType().getId() : null);
        return dto;
    }

    private void validateTestCaseDto(TestCaseDto dto) {
//        // description: string only, max 255
//        if (dto.getDescription() == null || dto.getDescription().length() > 255 || !dto.getDescription().matches("^[\\p{L}0-9 .,'\"!?-]*$")) {
//            throw new IllegalArgumentException("Description must be a string with max 255 characters.");
//        }
//
//        // steps: string only, max 255
//        if (dto.getSteps() == null || dto.getSteps().length() > 255 || !dto.getSteps().matches("^[\\p{L}0-9 .,'\"!?-]*$")) {
//            throw new IllegalArgumentException("Steps must be a string with max 255 characters.");
//        }
        // moduleId: must match MO + 4 digits, max 6
        if (dto.getModuleId() == null || !dto.getModuleId().matches("^MO\\d{4}$") || dto.getModuleId().length() != 6) {
            throw new IllegalArgumentException("moduleId must be in format MO0001 and 6 characters.");
        }
        // projectId: must match PR + 4 digits, max 6
        if (dto.getProjectId() == null || !dto.getProjectId().matches("^PR\\d{4}$") || dto.getProjectId().length() != 6) {
            throw new IllegalArgumentException("projectId must be in format PR0001 and 6 characters.");
        }
}
}
