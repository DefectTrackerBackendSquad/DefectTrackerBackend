package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Transactional
@Repository
public interface DefectRepo extends JpaRepository<Defect, Long> {

    @Query(value = "SELECT * FROM defect WHERE (:statusId IS NULL OR defect_status_id = :statusId) AND (:severityId IS NULL OR severity_id = :severityId) AND (:priorityId IS NULL OR priority_id = :priorityId) AND (:typeId IS NULL OR type_id = :typeId) AND project_id = :projectId", nativeQuery = true)
    List<Defect> findWithAllFilters(@Param("statusId") Long statusId,
                                    @Param("severityId") Long severityId,
                                    @Param("priorityId") Long priorityId,
                                    @Param("typeId") Long typeId,
                                    @Param("projectId") Long projectId);
    @Query("SELECT d FROM Defect d " +
            "LEFT JOIN FETCH d.assignedTo " +
            "LEFT JOIN FETCH d.project " +
            "LEFT JOIN FETCH d.defectStatus " +
            "WHERE d.assignedBy.id = :userId")
    List<Defect> findByAssignedById(@Param("userId") Long userId);
    Defect findByDefectId(String defectId);
    List<Defect> findAll();
}

