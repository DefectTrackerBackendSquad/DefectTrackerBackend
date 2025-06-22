package com.defect.defectTracker.service;
import com.defect.defectTracker.exception.ResourceNotFoundException;
import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.repository.DefectRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok generates constructor
public class DefectServiceImpl implements DefectService {

  private final DefectRepo defectRepo;

    @Override
    public DefectDto getDefectByTestcaseId(String testcaseId) {
        Defect defect = defectRepo.findByReleaseTestCaseId(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Case not found"));

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
