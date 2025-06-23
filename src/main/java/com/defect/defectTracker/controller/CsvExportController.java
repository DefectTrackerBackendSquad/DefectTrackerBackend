package com.defect.defectTracker.controller;

import com.defect.defectTracker.service.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/export")
public class CsvExportController {

    @Autowired
    private CsvExportService csvExportService;

    @GetMapping("/test-cases")
    public void exportTestCases(HttpServletResponse response) {
        csvExportService.exportTestCasesToCsv(response);
    }
}
