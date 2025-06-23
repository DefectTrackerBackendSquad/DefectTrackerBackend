package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
@RequiredArgsConstructor
@Transactional
public class TestCaseImportServiceImpl implements TestCaseImportService {

    @Autowired
    private TestCaseRepo testCaseRepository;

    private final TestCaseRepo testCaseRepo;
    private final SubModuleRepo subModuleRepo;
    private final ModuleRepo moduleRepo;
    private final ProjectRepo projectRepo;
    private final SeverityRepo severityRepo;
    private final TypeRepo typeRepo;

    @Transactional
    public void importTestCasesFromCsv(MultipartFile file) throws IOException {

        Logger logger = LoggerFactory.getLogger(TestCaseImportServiceImpl.class);

        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            logger.info("Starting import of test cases from CSV file");

            Long testCaseCount = testCaseRepository.findMaxId().orElse(0L);

            logger.info("Current test case count: {}", testCaseCount);


            for (CSVRecord record : csvParser) {
                TestCase testCase = new TestCase();

                testCase.setTestCaseId("TC" + String.format("%04d", ++testCaseCount));

                // Set basic fields
                testCase.setDescription(record.get("description"));
                testCase.setSteps(record.get("steps"));

                testCase.setSubModule(subModuleRepo.findById(
                        String.valueOf(Long.parseLong(record.get("sub_module_id")))).orElse(null));
                testCase.setModules(moduleRepo.findById(
                        String.valueOf(Long.parseLong(record.get("module_id")))).orElse(null));
                testCase.setProject(projectRepo.findById(
                        String.valueOf(Long.parseLong(record.get("project_id")))).orElse(null));
                testCase.setSeverity(severityRepo.findById(
                        Long.parseLong(record.get("severity_id"))).orElse(null));
                testCase.setType(typeRepo.findById(
                        Long.parseLong(record.get("type_id"))).orElse(null));

                testCaseRepository.save(testCase);
            }
        }
    }
}
