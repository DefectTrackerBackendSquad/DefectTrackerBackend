package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.ProjectAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAllocationRepo extends JpaRepository<ProjectAllocation, Long> {

}
