package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Releases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReleasesRepo extends JpaRepository<Releases, Long> {

    @Query("SELECT r FROM Releases r WHERE " +
            "(:releaseName IS NULL OR LOWER(r.releaseName) LIKE LOWER(CONCAT('%', :releaseName, '%'))) AND " +
            "(:releaseType IS NULL OR r.releaseType = :releaseType) AND " +
            "(:releaseDate IS NULL OR r.releaseDate = :releaseDate) AND " +
            "(:projectId IS NULL OR r.project.id = :projectId)")
    List<Releases> searchReleases(@Param("releaseName") String releaseName,
                                  @Param("releaseType") String releaseType,
                                  @Param("releaseDate") Date releaseDate,
                                  @Param("projectId") Long projectId);
}