package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.ReleaseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReleaseTestCaseRepo extends JpaRepository<ReleaseTestCase, Long> {
    Optional<ReleaseTestCase> findByReleaseTestCaseId(String releaseTestCaseId);
}
