package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Severity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeverityRepo extends JpaRepository<Severity, Long> {
}
