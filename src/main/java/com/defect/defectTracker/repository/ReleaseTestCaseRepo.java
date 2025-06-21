package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.ReleaseTestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseTestCaseRepo extends JpaRepository<ReleaseTestCase, Long> {
}


