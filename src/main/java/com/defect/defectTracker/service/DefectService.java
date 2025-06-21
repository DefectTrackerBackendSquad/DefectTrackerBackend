// DefectService.java
package com.example.defectTracker.service;

import com.example.defectTracker.entity.Defect;
import java.util.List;

public interface DefectService {
    Defect createDefect(Defect defect);
    Defect getDefectByDefectId(String defectId);
    List<Defect> getAllDefects();
    Defect updateDefect(Defect defect);
    void deleteDefect(String defectId);
}
