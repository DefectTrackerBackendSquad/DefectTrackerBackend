package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.exception.ResourceNotFoundException;
import com.defect.defectTracker.repository.*;
import com.defect.defectTracker.response.ApiResponse;
import com.defect.defectTracker.response.ApiResponseCodes;
import com.defect.defectTracker.utils.TestCaseMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    private static final Logger logger = Logger.getLogger(TestCaseServiceImpl.class.getName());

    private final TestCaseRepo testCaseRepo;
    private final SubModuleRepo subModuleRepo;
    private final ModuleRepo moduleRepo;
    private final ProjectRepo projectRepo;
    private final SeverityRepo severityRepo;
    private final TypeRepo typeRepo;

    @Autowired
    public TestCaseServiceImpl(
            TestCaseRepo testCaseRepo,
            SubModuleRepo subModuleRepo,
            ModuleRepo moduleRepo,
            ProjectRepo projectRepo,
            SeverityRepo severityRepo,
            TypeRepo typeRepo
    ) {
        this.testCaseRepo = testCaseRepo;
        this.subModuleRepo = subModuleRepo;
        this.moduleRepo = moduleRepo;
        this.projectRepo = projectRepo;
        this.severityRepo = severityRepo;
        this.typeRepo = typeRepo;
    }

    // ✅ Get test cases by Module ID as DTO list
    @Override
    public List<TestCaseDto> getTestCasesByModuleIdDto(Long moduleId) {
        List<TestCase> testCases = testCaseRepo.findByModules_Id(moduleId);
        return testCases.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // ✅ Get test cases by SubModule ID as DTO list
    @Override
    public List<TestCaseDto> getTestCasesBySubModuleId(Long subModuleId) {
        List<TestCase> testCases = testCaseRepo.findBySubModule_Id(subModuleId);
        return testCases.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // ✅ Delete test case by ID
    @Override
    public TestCaseDto deleteByTestCaseId(String testCaseId) {
        Optional<TestCase> optional = testCaseRepo.findByTestCaseId(testCaseId);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Test case with ID " + testCaseId + " not found");
        }
        testCaseRepo.deleteByTestCaseId(testCaseId);
        return mapToDto(optional.get());
    }

    // ✅ Check if test case exists
    @Override
    public boolean testCaseExists(String testCaseId) {
        return testCaseRepo.existsByTestCaseId(testCaseId);
    }

    // ✅ Create new test case
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

        return mapToDto(testCaseRepo.save(testCase));
    }

    // ✅ Update test case
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

    // ✅ Validation
    private void validateTestCaseDto(TestCaseDto dto) {
        if (dto.getModuleId() == null || !dto.getModuleId().matches("^MO\\d{4}$")) {
            throw new IllegalArgumentException("moduleId must be in format MO0001");
        }
        if (dto.getProjectId() == null || !dto.getProjectId().matches("^PR\\d{4}$")) {
            throw new IllegalArgumentException("projectId must be in format PR0001");
        }
    }

    // ✅ Mapping entity to DTO
    private TestCaseDto mapToDto(TestCase testCase) {
        TestCaseDto dto = new TestCaseDto();
        dto.setId(testCase.getId());
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
}
