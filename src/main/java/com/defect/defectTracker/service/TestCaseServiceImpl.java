package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.response.ApiResponse;
import com.defect.defectTracker.response.ApiResponseCodes;
import com.defect.defectTracker.util.TestCaseMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.defect.defectTracker.repository.*;
import com.defect.defectTracker.entity.TestCase;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Transactional
@Service
public class TestCaseServiceImpl implements TestCaseService {

    Logger logger = LoggerFactory.getLogger(TestCaseServiceImpl.class);

    @Autowired
    private TestCaseRepo testCaseRepository;

    @Autowired
    private TestCaseRepo testCaseRepo;

    @Override
    public List<TestCaseDto> getBySubModuleId(Long subModuleId) {
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
    public List<TestCaseDto> getByModuleId(Long moduleId) {
        List<TestCase> testCases = testCaseRepo.findByModules_Id(moduleId);
        return testCases.stream().map(testCase -> {
            TestCaseDto dto = new TestCaseDto();
            dto.setId(testCase.getId());
            logger.info(String.valueOf(testCase.getId()));
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
        Long maxId = testCaseRepo.findMaxId().orElse(0L);
        String newTestCaseId = String.format("TC%04d", maxId + 1);
        dto.setTestCaseId(newTestCaseId);

        validateTestCaseDto(dto);

        TestCase testCase = new TestCase();
        testCase.setTestCaseId(dto.getTestCaseId());
        testCase.setDescription(dto.getDescription());
        testCase.setSteps(dto.getSteps());

        if (dto.getSubModuleId() != null)
            testCase.setSubModule(subModuleRepo.findBySubModuleId(dto.getSubModuleId()).orElse(null));
        if (dto.getModuleId() != null)
            testCase.setModules((Modules) moduleRepo.findByModuleId(dto.getModuleId()).orElse(null));
        if (dto.getProjectId() != null)
            testCase.setProject(projectRepo.findByProjectId(dto.getProjectId()).orElse(null));
        if (dto.getSeverityId() != null)
            testCase.setSeverity(severityRepo.findById(dto.getSeverityId()).orElse(null));
        if (dto.getTypeId() != null)
            testCase.setType(typeRepo.findById(dto.getTypeId()).orElse(null));

        return mapToDto(testCaseRepo.save(testCase));
    }


    @Override
    public List<TestCaseDto> getTestCasesByProjectId(String projectId) {
        List<TestCase> testCases = testCaseRepository.findByProjectProjectId(projectId);

        return testCases.stream().map(testCase -> {
            TestCaseDto dto = new TestCaseDto();
            dto.setId(testCase.getId());
            dto.setDescription(testCase.getDescription());
            dto.setSteps(testCase.getSteps());
            dto.setTestCaseId(testCase.getTestCaseId());
            dto.setModuleId(testCase.getModules().getModuleId());
            dto.setProjectId(testCase.getProject().getProjectId());
            dto.setSeverityId(testCase.getSeverity().getId());
            dto.setSubModuleId(testCase.getSubModule().getSubModuleId());
            dto.setTypeId(testCase.getType().getId());
            return dto;
        }).collect(Collectors.toList());
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
        // description: string only, max 255
       if (dto.getDescription() == null || dto.getDescription().length() > 255 || !dto.getDescription().matches("^[\\p{L}0-9 .,'\"!?-]*$")) {
            throw new IllegalArgumentException("Description must be a string with max 255 characters.");
        }

        // steps: string only, max 255
        if (dto.getSteps() == null || dto.getSteps().length() > 255 || !dto.getSteps().matches("^[\\p{L}0-9 .,'\"!?-]*$")) {
           throw new IllegalArgumentException("Steps must be a string with max 255 characters.");
        }
        // moduleId: must match MO + 4 digits, max 6
        if (dto.getModuleId() == null || !dto.getModuleId().matches("^MO\\d{4}$") || dto.getModuleId().length() != 6) {
            throw new IllegalArgumentException("moduleId must be in format MO0001 and 6 characters.");
        }
        // projectId: must match PR + 4 digits, max 6
        if (dto.getProjectId() == null || !dto.getProjectId().matches("^PR\\d{4}$") || dto.getProjectId().length() != 6) {
            throw new IllegalArgumentException("projectId must be in format PR0001 and 6 characters.");
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updateTestCase(String testCaseId, TestCaseDto dto) {
        try {
            TestCase testCase = testCaseRepo.findByTestCaseId(testCaseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Test case not found with ID: " + testCaseId));

            SubModule subModule = subModuleRepo.findBySubModuleId(dto.getSubModuleId())
                    .orElseThrow(() -> new ResourceNotFoundException("SubModule not found with ID: " + dto.getSubModuleId()));

            Project project = projectRepo.findByProjectId(dto.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + dto.getProjectId()));

            Severity severity = severityRepo.findById(dto.getSeverityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Severity not found with ID: " + dto.getSeverityId()));

            Type type = typeRepo.findById(dto.getTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID: " + dto.getTypeId()));

            TestCaseMapper.mapDtoToEntity(dto, testCase, subModule, project, severity, type);

            testCaseRepo.save(testCase);

            return ResponseEntity.ok(new ApiResponse("Success", ApiResponseCodes.SUCCESS_UPDATE, ApiResponseCodes.MSG_UPDATE_SUCCESS));

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Failure", ApiResponseCodes.ERROR_DATA_NOT_FOUND, ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Failure", ApiResponseCodes.ERROR_UPDATE_FAILED, ApiResponseCodes.MSG_UPDATE_FAILED));
        }
    }



}



