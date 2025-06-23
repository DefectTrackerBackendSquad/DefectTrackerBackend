package com.defect.defectTracker.dto;

import lombok.Data;

@Data
public class TestCaseResponseDTO {
    private String releasedTestcaseId;
    private String testcaseId;
    private String testcase;
    private String description;
    private String releaseId;
    private String testDate;
    private String testTime;
    private String severityId;
    private String severity;
    private String steps;
    private String typeId;
    private String type;
    private int testcaseStatus;
}
