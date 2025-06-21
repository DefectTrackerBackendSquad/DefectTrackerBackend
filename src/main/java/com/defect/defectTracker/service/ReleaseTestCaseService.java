package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;

import java.util.List;

public interface ReleaseTestCaseService {
    List<ReleaseTestCaseDto> getTestCasesByReleaseId(String releaseId);

}
