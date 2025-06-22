package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;


@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, Long> {
    Optional<TestCase> findByTestCaseId(String testCaseId);
    List<TestCase> findByModules_Id(Long moduleId);
    List<TestCase> findBySubModule_Id(Long subModuleId);
    boolean existsByTestCaseId(String testCaseId);

    @Query("SELECT MAX(t.id) FROM TestCase t")
    Optional<Long> findMaxId();
    
}

