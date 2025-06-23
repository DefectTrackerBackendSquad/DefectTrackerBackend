package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDto;

import com.defect.defectTracker.utils.StandardResponse;

public interface DefectStatusService {
    StandardResponse deleteDefectStatus(Long defectStatusId);
    DefectStatusDto updateDefectStatus(Long id, DefectStatusDto dto);
}
