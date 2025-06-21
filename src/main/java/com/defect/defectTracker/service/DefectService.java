// DefectService.java
package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Defect;

import java.util.List;

public interface DefectService {
    Defect getDefectByDefectId(String defectId);

    Defect updateDefect(Defect defect);

}
