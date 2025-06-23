package com.defect.defectTracker.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.entity.User;
import com.defect.defectTracker.exceptionHandler.ReleaseTestCaseBadRequestException;
import com.defect.defectTracker.exceptionHandler.ReleaseTestCaseNotFoundException;
import com.defect.defectTracker.repository.ReleaseTestCaseRepo;
import com.defect.defectTracker.repository.ReleasesRepo;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.defect.defectTracker.dto.TestCaseResponseDTO;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {
    @Autowired
    private ReleaseTestCaseRepo releaseTestCaseRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TestCaseRepo testCaseRepo;
    @Autowired
    private ReleasesRepo releasesRepo;

    @Override
    public ReleaseTestCase updateReleaseTestCase(Long id, ReleaseTestCaseDto dto) {

        if (dto.getReleaseTestCaseId() == null || dto.getReleaseTestCaseId().trim().isEmpty()) {
            throw new ReleaseTestCaseBadRequestException(4000, "releaseTestCaseId is missing");
        }

        if (dto.getReleasesId() == null) {
            throw new ReleaseTestCaseBadRequestException(4000, "releaseId is required");
        }

        if (dto.getTestCaseId() == null) {
            throw new ReleaseTestCaseBadRequestException(4000, "testCaseId does not exist");
        }

        if (dto.getTestDate() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                sdf.parse(new SimpleDateFormat("yyyy-MM-dd").format(dto.getTestDate()));
            } catch (ParseException e) {
                throw new ReleaseTestCaseBadRequestException(4000, "Invalid testDate format. Expected YYYY-MM-DD");
            }
        } else {
            throw new ReleaseTestCaseBadRequestException(4000, "Invalid testDate format. Expected YYYY-MM-DD");
        }

        if (dto.getTestTime() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            try {
                sdf.parse(dto.getTestTime().toString());
            } catch (Exception e) {
                throw new ReleaseTestCaseBadRequestException(4000, "Invalid testTime format. Expected HH:mm:ss");
            }
        } else {
            throw new ReleaseTestCaseBadRequestException(4000, "Invalid testTime format. Expected HH:mm:ss");
        }

        if (dto.getUserId() == null) {
            throw new ReleaseTestCaseBadRequestException(4000, "Owner is required");
        }

        Optional<ReleaseTestCase> optional = releaseTestCaseRepo.findById(id);
        if (optional.isEmpty()) {
            throw new ReleaseTestCaseNotFoundException( "releaseTestCaseId not found");
        }

        ReleaseTestCase rtc = optional.get();
        rtc.setReleaseTestCaseId(dto.getReleaseTestCaseId());
        rtc.setTestDate(dto.getTestDate());
        rtc.setTestTime(dto.getTestTime());
        rtc.setTestCaseStatus(dto.getTestCaseStatus());
        if (dto.getUserId() != null) {
            User user = userRepo.findById(dto.getUserId().toString()).orElse(null);
            rtc.setUser(user);
        }
        if (dto.getTestCaseId() != null) {
            TestCase testCase = testCaseRepo.findById(dto.getTestCaseId().toString()).orElse(null);
            rtc.setTestCase(testCase);
        }
        if (dto.getReleasesId() != null) {
            Releases releases = releasesRepo.findById(dto.getReleasesId().toString()).orElse(null);
            rtc.setReleases(releases);
        }
        return releaseTestCaseRepo.save(rtc);
    }

    @Override
    public ReleaseTestCase createReleaseTestCase(ReleaseTestCaseDto dto) {
        ReleaseTestCase rtc = new ReleaseTestCase();
        rtc.setReleaseTestCaseId(dto.getReleaseTestCaseId());
        rtc.setTestDate(dto.getTestDate());
        rtc.setTestTime(dto.getTestTime());
        rtc.setTestCaseStatus(dto.getTestCaseStatus());
        if (dto.getUserId() != null) {
            User user = userRepo.findById(dto.getUserId().toString()).orElse(null);
            rtc.setUser(user);
        }
        if (dto.getTestCaseId() != null) {
            TestCase testCase = testCaseRepo.findById(dto.getTestCaseId().toString()).orElse(null);
            rtc.setTestCase(testCase);
        }
        if (dto.getReleasesId() != null) {
            Releases releases = releasesRepo.findById(dto.getReleasesId().toString()).orElse(null);
            rtc.setReleases(releases);
        }
        return releaseTestCaseRepo.save(rtc);
    }
  
    @Override
    public TestCaseResponseDTO getTestCaseByReleaseTestCaseId(String releaseTestCaseId) {
        ReleaseTestCase rtc = releaseTestCaseRepo.findByReleaseTestCaseId(releaseTestCaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found"));

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
    public void deleteTestCaseByReleaseTestCaseId(String releaseTestCaseId) {
        boolean exists = releaseTestCaseRepo.existsByReleaseTestCaseId(releaseTestCaseId);
        if (!exists) {
            throw new ResourceNotFoundException("ReleaseTestCase with ID " + releaseTestCaseId + " not found");
        }
        releaseTestCaseRepo.deleteByReleaseTestCaseId(releaseTestCaseId);
    }
}
