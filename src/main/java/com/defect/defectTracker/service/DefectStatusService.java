package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDTO;

import com.defect.defectTracker.utils.StandardResponse;

public interface DefectStatusService {
    DefectStatusDTO createDefectStatus(DefectStatusDTO defectStatusDTO);
    StandardResponse deleteDefectStatus(Long defectStatusId);
}


