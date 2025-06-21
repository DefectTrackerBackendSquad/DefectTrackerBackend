package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DefectRepo extends JpaRepository<Defect, Long> {
    List<Defect> findByProject_ProjectId(String projectId);
}
