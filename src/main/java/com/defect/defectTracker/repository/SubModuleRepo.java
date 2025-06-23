package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.SubModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SubModuleRepo extends JpaRepository<SubModule, Long> {
    Optional<SubModule> findBySubModuleId(String subModuleId);

}


