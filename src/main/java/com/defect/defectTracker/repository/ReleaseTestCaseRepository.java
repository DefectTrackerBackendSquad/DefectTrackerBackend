package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.ReleaseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseTestCaseRepository extends JpaRepository<ReleaseTestCase, Long> {
    // Find ReleaseTestCase by releaseTestCaseId (String type)
    ReleaseTestCase findByReleaseTestCaseId(String releaseTestCaseId);
}