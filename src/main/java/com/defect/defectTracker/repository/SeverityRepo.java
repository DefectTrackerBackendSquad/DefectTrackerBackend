package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Severity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeverityRepo extends JpaRepository<Severity, Long> {

    //  Find severity by its custom ID (e.g., "SEV001")
    Optional<Severity> findBySeverityName(String SeverityName);
}
