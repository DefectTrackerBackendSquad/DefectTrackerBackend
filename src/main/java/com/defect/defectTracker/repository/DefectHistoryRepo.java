package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.DefectHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DefectHistoryRepo extends JpaRepository<DefectHistory, Long> {
    List<DefectHistory> findByDefectId(Long defectId);
}
