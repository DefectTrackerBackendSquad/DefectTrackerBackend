package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.defect.defectTracker.entity.Defect;

public interface DefectService {
    Defect updateDefect(Defect defect);

    List<Defect> getDefectsByAssignee(Long userId);
    Defect getDefectByDefectId(String id);
}
