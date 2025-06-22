package com.defect.defectTracker.repository;


import com.defect.defectTracker.entity.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModuleRepo extends JpaRepository<Modules, String> {
    // Additional query methods can be defined here if needed
    Optional<Modules> findByModuleId(String moduleId);

}
