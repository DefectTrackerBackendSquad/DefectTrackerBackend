package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.entity.ReleaseTestCase;

public interface ReleaseTestCaseService {
    TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId);
    void deleteTestCaseByReleaseTestCaseId(String releaseTestCaseId);

    ReleaseTestCase allocateTestCaseRelease(ReleaseTestCaseDto releaseTestCaseDto);
}
