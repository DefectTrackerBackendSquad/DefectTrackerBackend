package com.defect.defectTracker.repository;


import com.defect.defectTracker.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {
    // Additional query methods can be defined here if needed
}
