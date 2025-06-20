package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepo extends JpaRepository<Priority, Long> {
    // Additional query methods can be defined here if needed
}
