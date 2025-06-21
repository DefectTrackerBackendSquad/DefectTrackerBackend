package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.DefectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DefectStatusRepo extends JpaRepository<DefectStatus, Long> {
    Optional<DefectStatus> findByDefectStatusName(String defectStatusName);
}
