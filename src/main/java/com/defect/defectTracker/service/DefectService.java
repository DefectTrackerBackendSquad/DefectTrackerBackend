package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.dto.DefectFilterResponseDTO;
import com.defect.defectTracker.entity.Defect;

import java.util.List;

public interface DefectService {
    Defect updateDefect(Defect defect);
    List<Defect> getDefectsByAssignee(Long userId);
    Defect getDefectByDefectId(String id);
    void createDefect(DefectDto dto);
    List<DefectFilterResponseDTO> filterDefects(String projectId, Long statusId, Long priorityId, Long severityId, Long typeId);
}


