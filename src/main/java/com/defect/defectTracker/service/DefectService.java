package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.utils.StandardResponse;

public interface DefectService {
    StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId);
}
