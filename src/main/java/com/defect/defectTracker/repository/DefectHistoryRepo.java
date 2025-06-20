package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.DefectHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefectHistoryRepo extends JpaRepository<DefectHistory, Long> {
}
