package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import java.util.List;
import java.util.List;
@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, Long> {

    Optional<TestCase> findByTestCaseId(String testCaseId);

    boolean existsByTestCaseId(String testCaseId);

    @Modifying
    @Query("DELETE FROM TestCase t WHERE t.testCaseId = :testCaseId")
    void deleteByTestCaseId(@Param("testCaseId") String testCaseId);

public interface TestCaseRepo extends JpaRepository<TestCase, Long> {
    List<TestCase> findByModules_Id(Long moduleId);

@Transactional
public interface TestCaseRepo extends JpaRepository<TestCase, String> {
    boolean existsByTestCaseId(String testCaseId);

    @Query("SELECT MAX(t.id) FROM TestCase t")
    Optional<Long> findMaxId();
    List<TestCase> findBySubModule_Id(Long subModuleId);
}
