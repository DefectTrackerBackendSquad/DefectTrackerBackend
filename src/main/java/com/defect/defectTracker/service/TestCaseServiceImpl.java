package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseDto;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.service.TestCaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

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
}
