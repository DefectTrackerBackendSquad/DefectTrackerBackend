package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.utils.StandardResponse;
import java.util.List;

public interface DefectService {
    StandardResponse createDefect(DefectDto defectDTO);
    List<DefectDto> getDefectsByAssignee(String userId);
}