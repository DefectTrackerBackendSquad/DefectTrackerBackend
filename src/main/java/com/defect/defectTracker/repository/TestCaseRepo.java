package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

import java.util.List;
@Transactional
@Repository
public interface TestCaseRepo extends JpaRepository<TestCase, Long> {
    List<TestCase> findByProjectProjectId(String projectId);

    ScopedValue<Long> findMaxId();

    public interface testCaseRepo extends JpaRepository<TestCase, String> {
    boolean existsByTestCaseId(String testCaseId);

    @Query("SELECT MAX(t.id) FROM TestCase t")
    Optional<Long> findMaxId();

//public interface TestCaseRepo extends JpaRepository<TestCase, String>
//    @Query("SELECT MAX(t.id) FROM TestCase t")
//    Optional<Long> findMaxId();
}
}
