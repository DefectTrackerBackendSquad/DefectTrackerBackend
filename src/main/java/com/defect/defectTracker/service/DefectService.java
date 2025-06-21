package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.utils.StandardResponse;
import com.defect.defectTracker.entity.Defect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DefectService {
    StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId);
    List<Defect> getDefectsByAssignee(Long userId);
    Defect getDefectByDefectId(String id);
}
