package com.defect.defectTracker.repository;
import com.defect.defectTracker.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UpdateTestCaseRepo extends JpaRepository<TestCase, Long> {

    Optional<TestCase> findByTestCaseId(String testCaseId);
}
