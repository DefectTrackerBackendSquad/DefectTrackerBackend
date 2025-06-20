package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.SubModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubModuleRepo extends JpaRepository<SubModule, String> {
}
