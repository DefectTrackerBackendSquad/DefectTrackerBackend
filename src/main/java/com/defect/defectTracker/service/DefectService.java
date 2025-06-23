package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.entity.Defect;

import java.util.List;

public interface DefectService {
    StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId);
    Defect updateDefect(Defect defect);
    List<Defect> getDefectsByAssignee(Long userId);
    Defect getDefectByDefectId(String id);
    StandardResponse buildResponse(List<Defect> defects);
}
