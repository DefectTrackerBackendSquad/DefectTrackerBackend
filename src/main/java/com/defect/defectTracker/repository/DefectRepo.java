package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import java.util.Optional;

public interface DefectRepo extends JpaRepository<Defect, Long> {
    @Query("SELECT d FROM Defect d WHERE d.releaseTestCase.releaseTestCaseId = :releaseTestCaseId")
    Optional<Defect> findByReleaseTestCaseId(@Param("releaseTestCaseId") String releaseTestCaseId);

   // @Query("SELECT d FROM Defect d WHERE d.releaseTestCase.testcaseId = :testcaseId")
    //Optional<Defect> findByTestcaseId(@Param("testcaseId") String testcaseId);
    //Optional<Defect> findByReleaseTestCaseId(@Param("releaseTestCaseId") String releaseTestCaseId);
   // Optional<Defect> findByReleaseTestCase_ReleaseTestCaseId(String releaseTestCaseId);



}
