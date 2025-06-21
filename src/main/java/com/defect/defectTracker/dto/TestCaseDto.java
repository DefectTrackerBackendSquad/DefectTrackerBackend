package com.defect.defectTracker.dto;

import lombok.Data;

@Data
public class TestCaseDto {
    private String testCaseId;
    private String description;
    private String steps;
    private String subModuleId;
    private String moduleId;
    private String projectId;
    private Long severityId;
    private Long typeId;
}
