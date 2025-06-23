package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Defect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface DefectRepo extends JpaRepository<Defect, Long> {

    // CREATE operations are inherited from JpaRepository (save(), saveAll())

    // READ operations
    @Query("SELECT d FROM Defect d " +
            "LEFT JOIN FETCH d.assignedTo " +
            "LEFT JOIN FETCH d.project " +
            "LEFT JOIN FETCH d.defectStatus " +
            "WHERE d.assignedTo.id = :userId")
    List<Defect> findByAssignedToId(@Param("userId") String userId);

    Defect findByDefectId(String defectId);

    Long countByDefectIdStartingWith(String prefix);

    @Query("SELECT d FROM Defect d " +
            "LEFT JOIN FETCH d.assignedTo " +
            "LEFT JOIN FETCH d.assignedBy " +
            "LEFT JOIN FETCH d.project " +
            "LEFT JOIN FETCH d.defectStatus " +
            "LEFT JOIN FETCH d.severity " +
            "LEFT JOIN FETCH d.priority " +
            "LEFT JOIN FETCH d.defectType " +
            "LEFT JOIN FETCH d.releaseTestCase")
    List<Defect> findAllWithRelationships();

    @Query("SELECT d FROM Defect d JOIN FETCH d.assignedBy WHERE d.assignedBy.id = :userId")
    List<Defect> findByAssignedById(@Param("userId") String userId);

    // Find by assignedBy (userId)
    List<Defect> findByAssignedBy_UserId(String userId);

    // UPDATE operations
    @Transactional
    @Modifying
    @Query("UPDATE Defect d SET " +
            "d.description = :description, " +
            "d.steps = :steps, " +
            "d.attachment = :attachment, " +
            "d.defectStatus = :statusId " +
            "WHERE d.defectId = :defectId")
    int updateDefect(@Param("defectId") String defectId,
                     @Param("description") String description,
                     @Param("steps") String steps,
                     @Param("attachment") String attachment,
                     @Param("statusId") Long statusId);

    // Custom save operation for defect creation
    @Transactional
    default Defect createDefect(Defect defect) {
        // Generate defect ID if not set
        if (defect.getDefectId() == null) {
            Long count = countByDefectIdStartingWith("DF");
            String newDefectId = String.format("DF%05d", count + 1);
            defect.setDefectId(newDefectId);
        }
        return save(defect);
    }
}