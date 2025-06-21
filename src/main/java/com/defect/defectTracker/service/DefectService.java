package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.entity.Defect;

public interface DefectService {
    StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId);
    Defect getDefectByDefectId(String id);
}
