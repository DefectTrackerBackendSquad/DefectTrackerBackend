package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;

public interface ReleaseTestCaseService {
    ReleaseTestCase updateReleaseTestCase(Long id, ReleaseTestCaseDto dto);
    ReleaseTestCase createReleaseTestCase(ReleaseTestCaseDto dto);
}
