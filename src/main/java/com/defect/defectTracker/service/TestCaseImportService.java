package com.defect.defectTracker.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TestCaseImportService {
    void importTestCasesFromCsv(MultipartFile file) throws IOException;

}
