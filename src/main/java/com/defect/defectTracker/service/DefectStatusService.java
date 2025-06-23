package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDto;

import com.defect.defectTracker.utils.StandardResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectStatusService {
    DefectStatusDto updateDefectStatus(Long defectStatusId, DefectStatusDto dto);
    StandardResponse deleteDefectStatus(Long defectStatusId);
}
