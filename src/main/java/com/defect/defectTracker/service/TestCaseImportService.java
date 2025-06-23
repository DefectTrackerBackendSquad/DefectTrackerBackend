package com.defect.defectTracker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service  // <<< This is mandatory
public class TestCaseImportService {

    public void importTestCasesFromCsv(MultipartFile file) throws IOException {
        System.out.println("Importing test cases from CSV: " + file.getOriginalFilename());
        // You can implement parsing here later
    }
}
