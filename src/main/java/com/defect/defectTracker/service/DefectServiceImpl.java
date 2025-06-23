package com.defect.defectTracker.service;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.*;
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
        Defect defect = defectRepo.findByReleaseTestCaseId(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Case not found"));


        //Defect defect = defectRepo.findByReleaseTestCase_ReleaseTestCaseId(testcaseId)
               // .orElseThrow(() -> new ResourceNotFoundException("Test case not found"));

        DefectDto dto = new DefectDto();
        dto.setId(defect.getDefectId());
        dto.setDefectTitle(defect.getDescription());
        dto.setDescriptions(defect.getDescription());
      //  dto.setTestcaseId(defect.getReleaseTestCase().getReleaseTestCaseId());
        dto.setSeverity(defect.getSeverity().getSeverityName());
        dto.setDefectStatus(defect.getDefectStatus().getDefectStatusName());

        return dto;
    }
}
