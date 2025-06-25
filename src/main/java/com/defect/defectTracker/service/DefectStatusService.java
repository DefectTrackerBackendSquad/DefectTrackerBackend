package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDto;

import com.defect.defectTracker.util.StandardResponse;

import java.util.List;

public interface DefectStatusService {
    StandardResponse deleteDefectStatus(Long defectStatusId);
    DefectStatusDto updateDefectStatus(Long id, DefectStatusDto dto);
    DefectStatusDto createDefectStatus(DefectStatusDto defectStatusDto);
    List<DefectStatusDto> getDefectStatuses();
}
