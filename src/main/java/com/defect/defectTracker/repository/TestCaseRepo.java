package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, Long> {

    Optional<TestCase> findByTestCaseId(String testCaseId);

    boolean existsByTestCaseId(String testCaseId);

    @Modifying
    @Query("DELETE FROM TestCase t WHERE t.testCaseId = :testCaseId")
    void deleteByTestCaseId(@Param("testCaseId") String testCaseId);
}
