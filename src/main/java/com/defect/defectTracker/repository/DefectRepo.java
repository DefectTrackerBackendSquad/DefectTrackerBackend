package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.Optional;

public interface DefectRepo extends JpaRepository<Defect, Long> {
    @Query("SELECT d FROM Defect d WHERE d.releaseTestCase.releaseTestCaseId = :releaseTestCaseId")
    Optional<Defect> findByReleaseTestCaseId(@Param("releaseTestCaseId") String releaseTestCaseId);




}

