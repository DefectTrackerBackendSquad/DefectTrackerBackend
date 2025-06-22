package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.TestCase;
import com.defect.defectTracker.repository.TestCaseRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class CsvExportService {

    @Autowired
    private TestCaseRepo testCaseRepository;

    public void exportTestCasesToCsv(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=testcases.csv");

        try (PrintWriter writer = response.getWriter();
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader("TestCaseID", "Description", "Steps", "Severity", "Type"))) {

            List<TestCase> testCases = testCaseRepository.findAll();

            for (TestCase tc : testCases) {
                csvPrinter.printRecord(
                        tc.getTestCaseId(),
                        tc.getDescription(),
                        tc.getSteps(),
                        tc.getSeverity() != null ? tc.getSeverity().getSeverityName() : "",
                        tc.getType() != null ? tc.getType().getTypeName() : ""
                );
            }

            csvPrinter.flush();

        } catch (IOException e) {
            throw new RuntimeException("Failed to export CSV", e);
        }
    }
}
