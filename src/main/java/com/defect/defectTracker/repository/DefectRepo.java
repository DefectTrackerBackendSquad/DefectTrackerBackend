package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DefectRepo extends JpaRepository<Defect, String> {
    Defect findByDefectId(String id);
}