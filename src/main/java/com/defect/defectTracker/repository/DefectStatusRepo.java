package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.DefectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
public interface DefectStatusRepo extends JpaRepository<DefectStatus, Long> {
    Optional<DefectStatus> findByDefectStatusNameIgnoreCase(String defectStatusName);
}
