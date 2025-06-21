// src/main/java/com/example/defectTracker/repository/ReleaseRepository.java
package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Releases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseRepo extends JpaRepository<Releases, Long> {
    Optional<Releases> findByReleaseId(String releaseId);
//    Optional<Releases> getReleaseByReleaseId(String releaseId);
}