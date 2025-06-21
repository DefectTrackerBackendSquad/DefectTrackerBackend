package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.ReleaseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseTestCaseRepo extends JpaRepository<ReleaseTestCase, String> {
    List<ReleaseTestCase> findByReleasesReleaseId(String releaseId);
}
