package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectRepo extends JpaRepository<Defect, Long> {
    Defect findByDefectId(String defectId);
    List<Defect> findAll();
  
}