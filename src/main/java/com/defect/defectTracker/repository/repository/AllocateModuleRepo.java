package com.defect.defectTracker.repository.repository;

import com.defect.defectTracker.entity.AllocateModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocateModuleRepo extends JpaRepository<AllocateModule, String> {
}
