package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.ReleasesRepo;
import com.defect.defectTracker.repository.ReleaseTestCaseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {

    private final ReleaseTestCaseRepo releaseTestCaseRepo;
    private final ReleasesRepo releaseRepo;

    @Override
    public List<ReleaseTestCaseDto> getTestCasesByReleaseId(String releaseId) {
        if (!releaseRepo.existsByReleaseId(releaseId)) {
            throw new ResourceNotFoundException("Release ID " + releaseId + " not found");
        }

        List<ReleaseTestCase> releaseTestCases = releaseTestCaseRepo.findByReleasesReleaseId(releaseId);

        return releaseTestCases.stream().map(rtc -> {
            ReleaseTestCaseDto dto = new ReleaseTestCaseDto();
            dto.setReleaseTestCaseId(rtc.getId());
            dto.setTestCaseId(rtc.getTestCase().getTestCaseId());
            dto.setTestCaseDescription(rtc.getTestCase().getDescription());
            dto.setTestSteps(rtc.getTestCase().getSteps());
            dto.setTestDate(rtc.getTestDate());
            dto.setTestTime(rtc.getTestTime());
            dto.setTestCaseStatus(rtc.getTestCaseStatus());

            dto.setReleaseId(rtc.getReleases().getReleaseId());
            return dto;
        }).collect(Collectors.toList());
    }
}
