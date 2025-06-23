package com.defect.defectTracker.repository;


import com.defect.defectTracker.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleRepo extends JpaRepository<Modules, String> {
    Optional<Object> findByModuleId(String moduleId);
    // Additional query methods can be defined here if needed
}
