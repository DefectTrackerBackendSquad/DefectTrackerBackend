package com.defect.defectTracker.service;

import com.defect.defectTracker.response.ApiResponseCodes;
import com.defect.defectTracker.dto.UpdateTestCaseDto;
import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.utils.TestCaseMapper;
import com.defect.defectTracker.repository.*;
import com.defect.defectTracker.response.ApiResponse;


import com.defect.defectTracker.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor // Auto-generates constructor for all final fields
public class UpdateTestCaseServiceImpl implements UpdateTestCaseService {

    private final TestCaseRepo testCaseRepository;
    private final SubModuleRepo subModuleRepository;
    private final ProjectRepo projectRepository;
    private final SeverityRepo severityRepository;
    private final TypeRepo typeRepository;

    @Override
    public ResponseEntity<ApiResponse> updateTestCase(String testCaseId, UpdateTestCaseDto dto) {
        try {
            // Find existing test case by testCaseId
            TestCase testCase = testCaseRepository.findByTestCaseId(testCaseId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Test case not found with ID: " + testCaseId));

            //  Fetch all related entities
      SubModule subModule = subModuleRepository.findBySubModuleId(dto.getSubModuleId())
               .orElseThrow(() -> new ResourceNotFoundException(
                        "SubModule not found with ID: " + dto.getSubModuleId()));

           Project project = projectRepository.findByProjectId(dto.getProjectId())
                  .orElseThrow(() -> new ResourceNotFoundException(
                       "Project not found with ID: " + dto.getProjectId()));
            // Fetch Severity object (required for @ManyToOne)
            Severity severity = severityRepository.findById(dto.getSeverityId())
                    .orElseThrow(() -> new ResourceNotFoundException("Severity not found with ID: " + dto.getSeverityId()));
            testCase.setSeverity(severity);

          Type type = typeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                           "Type not found with ID: " + dto.getTypeId()));

            //  mapper to update entity fields
            TestCaseMapper.mapDtoToEntity(dto, testCase, subModule, project, severity, type);

            // Save put entity
            testCaseRepository.save(testCase);

            return ResponseEntity.ok(
                    new ApiResponse("Success", ApiResponseCodes.SUCCESS_UPDATE, ApiResponseCodes.MSG_UPDATE_SUCCESS)
            );

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Failure", ApiResponseCodes.ERROR_DATA_NOT_FOUND, ex.getMessage())
            );

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Failure", ApiResponseCodes.ERROR_UPDATE_FAILED, ApiResponseCodes.MSG_UPDATE_FAILED)
            );
        }
    }
}
