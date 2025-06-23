package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseTestCaseDto;
import com.defect.defectTracker.entity.ReleaseTestCase;
import com.defect.defectTracker.repository.ReleaseTestCaseRepository;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseTestCaseServiceImpl implements ReleaseTestCaseService {

    private final ReleaseTestCaseRepository releaseTestCaseRepository;

    @Autowired
    public ReleaseTestCaseServiceImpl(ReleaseTestCaseRepository releaseTestCaseRepository) {
        this.releaseTestCaseRepository = releaseTestCaseRepository;
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