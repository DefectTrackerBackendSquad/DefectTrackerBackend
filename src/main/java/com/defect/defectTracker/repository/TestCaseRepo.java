package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, String> {
    List<TestCase> findBySubModule_Id(Long subModuleId);
}
