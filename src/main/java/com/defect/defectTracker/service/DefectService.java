package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;

public interface DefectService {
    Defect getDefectByDefectId(String id);
}