package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;

public interface DefectService {
    DefectDto getDefectByTestcaseId(String testcaseId);
}
