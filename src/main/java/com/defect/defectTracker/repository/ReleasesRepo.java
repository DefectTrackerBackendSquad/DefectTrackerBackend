package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Releases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleasesRepo extends JpaRepository<Releases, String> {
    boolean existsByReleaseId(String releaseId);
}
