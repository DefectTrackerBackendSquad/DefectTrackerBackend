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

    @Query(value = "SELECT * FROM defect WHERE (:statusId IS NULL OR defect_status_id = :statusId) AND (:severityId IS NULL OR severity_id = :severityId) AND (:priorityId IS NULL OR priority_id = :priorityId) AND (:typeId IS NULL OR type_id = :typeId) AND project_id = :projectId", nativeQuery = true)
    List<Defect> findWithAllFilters(@Param("statusId") Long statusId,
                                    @Param("severityId") Long severityId,
                                    @Param("priorityId") Long priorityId,
                                    @Param("typeId") Long typeId,
                                    @Param("projectId") Long projectId);
}

