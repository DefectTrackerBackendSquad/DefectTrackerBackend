

package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.dto.TestCaseResponseDTO;

public interface ReleaseTestCaseService {
    TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId);
    void deleteTestCaseByReleaseTestCaseId(String releaseTestCaseId);
    ReleaseTestCase updateReleaseTestCase(Long id, ReleaseTestCaseDto dto);
    ReleaseTestCase createReleaseTestCase(ReleaseTestCaseDto dto);
}
