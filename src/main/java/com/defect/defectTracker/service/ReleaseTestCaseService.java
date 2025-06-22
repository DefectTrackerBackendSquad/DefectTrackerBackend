package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.repository.ReleaseTestCaseRepository;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.defect.defectTracker.utils.ErrorResponse;

@Service
public interface ReleaseTestCaseService {

    public final ReleaseTestCaseRepository releaseTestCaseRepository = null;

    @Transactional
    public default Object deleteReleaseTestCase(String releaseTestCaseId) {
        // Validate input
        if (!StringUtils.hasText(releaseTestCaseId)) {
            throw new IllegalArgumentException("Release test case ID cannot be null or empty");
        }

        String trimmedId = releaseTestCaseId.trim();

        // Find the ReleaseTestCase by its ID (without Optional)
        ReleaseTestCase releaseTestCase = releaseTestCaseRepository.findByReleaseTestCaseId(trimmedId);

        if (releaseTestCase == null) {
            throw new ResourceNotFoundException(
                    String.format("Release test case not found with ID: %s", trimmedId)
            );
        }

        // Perform deletion
        releaseTestCaseRepository.delete(releaseTestCase);

        ErrorResponse response = new ErrorResponse();
        response.setStatus("success");
        response.setStatusCode("200");
        response.setMessage(String.format("Release test case '%s' deleted successfully", trimmedId));

        return response;
    }

    // Additional helper methods can be added below
}