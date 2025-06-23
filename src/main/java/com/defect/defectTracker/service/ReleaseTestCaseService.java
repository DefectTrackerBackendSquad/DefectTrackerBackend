package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseResponseDTO;

public interface ReleaseTestCaseService {
    TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId);
    void deleteTestCaseByReleaseTestCaseId(String releaseTestCaseId);
}
