package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDto;

public interface DefectStatusService {
    DefectStatusDto updateDefectStatus(Long id, DefectStatusDto dto);
}
