package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDto;

public interface DefectStatusService {
    DefectStatusDto updateDefectStatus(Long id, DefectStatusDto dto);

import com.defect.defectTracker.utils.StandardResponse;

public interface DefectStatusService {
    StandardResponse deleteDefectStatus(Long defectStatusId);
}
