package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TypeRepo extends JpaRepository<Type, Long> {
    Optional<Type> findByTypeId(String typeId);
}