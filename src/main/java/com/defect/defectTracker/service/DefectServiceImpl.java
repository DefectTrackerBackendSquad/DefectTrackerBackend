package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.exceptionHandler.GlobalExceptionHandler;
import com.defect.defectTracker.repository.DefectRepo;
import com.defect.defectTracker.service.DefectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepo defectRepo;

    @Override
    public DefectDto getDefectByTestcaseId(String testcaseId) {
        Defect defect = defectRepo.findByTestcaseId(testcaseId)
                .orElseThrow(() -> new GlobalExceptionHandler("Test Case not found"));

        DefectDto dto = new DefectDto();
        dto.setId(defect.getDefectId());
        dto.setDefectTitle(defect.getDescription());
        dto.setDescriptions(defect.getDescription());
        dto.setTestcaseId(defect.getReleaseTestCase().getTestcaseId());
        dto.setSeverity(defect.getSeverity().getSeverityName());
        dto.setDefectStatus(defect.getDefectStatus().getDefectStatusName());

        return dto;
    }
}
