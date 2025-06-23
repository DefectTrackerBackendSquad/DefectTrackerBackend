package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.List;
import java.util.List;
@Repository
@Transactional
public interface TestCaseRepo extends JpaRepository<TestCase, Long> {
    List<TestCase> findByProjectProjectId(String projectId);
    Optional<TestCase> findByTestCaseId(String testCaseId);
    boolean existsByTestCaseId(String testCaseId);
    @Modifying
    @Query("DELETE FROM TestCase t WHERE t.testCaseId = :testCaseId")
    void deleteByTestCaseId(@Param("testCaseId") String testCaseId);
    List<TestCase> findByModules_Id(Long moduleId);

    @Query("SELECT MAX(t.id) FROM TestCase t")
    Optional<Long> findMaxId();
    List<TestCase> findBySubModule_Id(Long subModuleId);
}
