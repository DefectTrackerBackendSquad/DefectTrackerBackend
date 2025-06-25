package com.defect.defectTracker.dto;

import lombok.Data;

@Data
public class DefectFilterResponseDTO {
    private Long defectId;
    private String descriptions;
    private String testCaseId;
    private String severity;
    private String priority;
    private String type;
    private String assignBy;
    private String assignTo;
    private String project;
    private String status;
    private int reopenCount;
    private String attachment;
    private String steps;
    private String releaseTestCase;
    private String moduleId;
    private String subModuleId;
}