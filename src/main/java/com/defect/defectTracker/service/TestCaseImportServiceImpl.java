package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.repository.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class TestCaseImportServiceImpl implements TestCaseImportService {

    private final TestCaseRepo testCaseRepo;
    private final ModuleRepo moduleRepo;
    private final ProjectRepo projectRepo;
    private final SubModuleRepo subModuleRepo;
    private final SeverityRepo severityRepo;
    private final TypeRepo typeRepo;

    public TestCaseImportServiceImpl(TestCaseRepo testCaseRepo,
                                     ModuleRepo moduleRepo,
                                     ProjectRepo projectRepo,
                                     SubModuleRepo subModuleRepo,
                                     SeverityRepo severityRepo,
                                     TypeRepo typeRepo) {
        this.testCaseRepo = testCaseRepo;
        this.moduleRepo = moduleRepo;
        this.projectRepo = projectRepo;
        this.subModuleRepo = subModuleRepo;
        this.severityRepo = severityRepo;
        this.typeRepo = typeRepo;
    }

    @Override
    public void importTestCasesFromCsv(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            for (CSVRecord csvRecord : csvParser) {
                TestCase testCase = new TestCase();

                testCase.setTestCaseId(csvRecord.get("testCaseId"));
                testCase.setDescription(csvRecord.get("description"));
                testCase.setSteps(csvRecord.get("steps"));

                String moduleId = csvRecord.get("moduleId");
                moduleRepo.findByModuleId(moduleId).ifPresent(testCase::setModules);

                String projectId = csvRecord.get("projectId");
                projectRepo.findByProjectId(projectId).ifPresent(testCase::setProject);

                String subModuleId = csvRecord.get("subModuleId");
                subModuleRepo.findBySubModuleId(subModuleId).ifPresent(testCase::setSubModule);

                Long severityId = Long.parseLong(csvRecord.get("severityId"));
                severityRepo.findById(severityId).ifPresent(testCase::setSeverity);

                Long typeId = Long.parseLong(csvRecord.get("typeId"));
                typeRepo.findById(typeId).ifPresent(testCase::setType);

                testCaseRepo.save(testCase);
            }
        }
    }
}
