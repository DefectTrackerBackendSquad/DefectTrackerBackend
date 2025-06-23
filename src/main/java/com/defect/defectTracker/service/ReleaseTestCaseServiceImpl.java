package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.repository.ReleaseTestCaseRepo;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {

    @Autowired
    private ReleaseTestCaseRepo releaseTestCaseRepo;

    @Override
    public TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId) {
        ReleaseTestCase rtc = releaseTestCaseRepo.findByReleaseTestCaseId(releaseTestCaseId)
                .orElseThrow(() -> new NoSuchElementException("Data not found"));

        TestCaseResponseDTO dto = new TestCaseResponseDTO();
        dto.setReleasedTestcaseId(rtc.getReleaseTestCaseId());
        dto.setTestcaseId(rtc.getTestCase().getTestCaseId());
        dto.setTestcase(rtc.getTestCase().getDescription());
        dto.setDescription(rtc.getTestCase().getDescription());
        dto.setReleaseId(rtc.getReleases().getReleaseId());
        dto.setTestDate(rtc.getTestDate().toString());
        dto.setTestTime(rtc.getTestTime().toString().substring(0, 5));
        dto.setSeverityId(rtc.getTestCase().getSeverity().getId().toString());
        dto.setSeverity(rtc.getTestCase().getSeverity().getSeverityName());
        dto.setSteps(rtc.getTestCase().getSteps());
        dto.setTypeId(rtc.getTestCase().getType().getId().toString());
        dto.setType(rtc.getTestCase().getType().getTypeName());
        dto.setTestcaseStatus("Passed".equalsIgnoreCase(rtc.getTestCaseStatus()) ? 1 : 0);

        return dto;
    }
  
    @Override
    public Object deleteReleaseTestCase(String releaseTestCaseId) {
        // Find the ReleaseTestCase by its ID
        ReleaseTestCase releaseTestCase = releaseTestCaseRepository.findByReleaseTestCaseId(releaseTestCaseId);

        if (releaseTestCase == null) {
            throw new ResourceNotFoundException("Release test case not found with the provided ID.");
        }

        // Perform deletion (cascade settings in the entity should handle related associations)
        releaseTestCaseRepository.delete(releaseTestCase);

        return "Release test case deleted successfully.";
    }

    public ReleaseTestCaseDto createReleaseTestCase(ReleaseTestCaseDto dto) {
        return null;
    }


    public List<ReleaseTestCaseDto> getTestCasesByReleaseId(String releaseId) {
        return List.of();
    }
}
