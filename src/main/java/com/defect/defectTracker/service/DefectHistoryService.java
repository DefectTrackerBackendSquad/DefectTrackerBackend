package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectHistoryDto;

import java.util.List;

public interface DefectHistoryService {
    List<DefectHistoryDto> getDefectHistoriesByDefectId(Long defectId);
}
