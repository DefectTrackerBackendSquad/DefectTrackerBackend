package com.defect.defectTracker.service;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.repository.ReleasesRepo;
import com.defect.defectTracker.repository.ReleaseTestCaseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public abstract class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {

    @Autowired
    private ReleaseTestCaseRepo releaseTestCaseRepo;

    @Autowired
    private TestCaseRepo testCaseRepo;

    @Autowired
    private ReleasesRepo releasesRepo;

    @Override
    public ReleaseTestCaseDto createReleaseTestCase(ReleaseTestCaseDto dto) {
        ReleaseTestCase releaseTestCase = new ReleaseTestCase();
        releaseTestCase.setReleaseTestCaseId(dto.getReleaseTestCaseId());
        releaseTestCase.setTestDate(dto.getTestDate());
        releaseTestCase.setTestTime(dto.getTestTime());
        releaseTestCase.setTestCaseStatus(dto.getTestCaseStatus());

        TestCase testCase = testCaseRepo.findById(dto.getTestCaseId()).orElse(null);
        Releases releases = releasesRepo.findById(dto.getReleaseId()).orElse(null);

        releaseTestCase.setTestCase(testCase);
        releaseTestCase.setReleases(releases);

        ReleaseTestCase saved = releaseTestCaseRepo.save(releaseTestCase);
        dto.setReleaseTestCaseId(saved.getReleaseTestCaseId());

        return dto;
    }
    @Override
    public List<ReleaseTestCaseDto> getTestCasesByReleaseId(String releaseId) {


        List<ReleaseTestCase> releaseTestCases = releaseTestCaseRepo.findByReleasesReleaseId(releaseId);

        return releaseTestCases.stream().map(rtc -> {
            ReleaseTestCaseDto dto = new ReleaseTestCaseDto();
            dto.setReleaseTestCaseId(rtc.getReleaseTestCaseId());
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