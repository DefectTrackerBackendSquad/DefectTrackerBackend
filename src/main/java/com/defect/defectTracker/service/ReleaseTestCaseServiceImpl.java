package com.defect.defectTracker.service.impl;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.ReleaseTestCaseRepo;
import com.defect.defectTracker.repository.ReleasesRepo;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.service.ReleaseTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {

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
        dto.setId(saved.getId());

        return dto;
    }


}
