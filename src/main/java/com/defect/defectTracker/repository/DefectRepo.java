package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefectRepo extends JpaRepository<Defect, Long> {
    @Query("SELECT d FROM Defect d WHERE d.releaseTestCase.releaseTestCaseId = :releaseTestCaseId")
    Optional<Defect> findByReleaseTestCaseId(@Param("releaseTestCaseId") String releaseTestCaseId);
       @Query("SELECT d FROM Defect d " +
               "WHERE (:projectId IS NULL OR d.project.projectId = :projectId) " +
               "AND (:statusId IS NULL OR d.defectStatus.id = :statusId) " +
               "AND (:priorityId IS NULL OR d.priority.id = :priorityId) " +
               "AND (:severityId IS NULL OR d.severity.id = :severityId) " +
               "AND (:typeId IS NULL OR d.defectType.id = :typeId)")
       List<Defect> filterDefects(
               @Param("projectId") String projectId,
               @Param("statusId") Long statusId,
               @Param("priorityId") Long priorityId,
               @Param("severityId") Long severityId,
               @Param("typeId") Long typeId);
    List<Defect> findByAssignedById(@Param("userId") Long userId);
    Defect findByDefectId(String defectId);
    List<Defect> findAll();
}
