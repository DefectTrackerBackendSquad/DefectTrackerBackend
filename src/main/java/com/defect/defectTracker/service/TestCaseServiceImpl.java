package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
import com.defect.defectTracker.service.TestCaseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

    Logger logger = Logger.getLogger(TestCaseServiceImpl.class.getName());

    @Autowired
    private TestCaseRepo testCaseRepository;
    @Override
    public List<TestCase> getTestCasesByModuleId(Long moduleId) {
        List<TestCase> testCases =  testCaseRepository.findByModules_Id(moduleId);
        logger.info(String.valueOf(testCases.size()));
        return testCases;
    }
}
