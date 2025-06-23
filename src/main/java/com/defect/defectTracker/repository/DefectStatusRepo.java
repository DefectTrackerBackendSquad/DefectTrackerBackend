package com.defect.defectTracker.repository;


import com.defect.defectTracker.entity.DefectStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface DefectStatusRepo extends JpaRepository<DefectStatus, Long> {

    //void deleteDefectStatus (Long defectStatusId);
}


