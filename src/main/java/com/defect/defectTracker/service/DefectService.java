package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDTO;
import com.defect.defectTracker.utils.StandardResponse;
import java.util.List;

public interface DefectService {
    StandardResponse createDefect(DefectDTO defectDTO);
    List<DefectDTO> getDefectsByAssignee(String userId);
}