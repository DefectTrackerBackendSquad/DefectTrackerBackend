package com.defect.defectTracker.repository;


import com.defect.defectTracker.entity.DefectStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface DefectStatusRepo extends JpaRepository<DefectStatus, Long> {
    Optional<DefectStatus> findByDefectStatusNameIgnoreCase(String defectStatusName);

    //void deleteDefectStatus (Long defectStatusId);
}
