package com.defect.defectTracker.repository;

import com.defect.defectTracker.entity.TestCase;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
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
    List<TestCase> findByModules_Id(Long moduleId);

    @Query("SELECT MAX(t.id) FROM TestCase t")
    Optional<Long> findMaxId();
    List<TestCase> findBySubModule_Id(Long subModuleId);
}




