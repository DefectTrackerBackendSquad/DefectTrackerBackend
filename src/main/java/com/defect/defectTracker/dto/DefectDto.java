package com.defect.defectTracker.dto;

import lombok.Data;

@Data
public class DefectDto {
    private String message; // used for response message
    private String defectId;
    private String description;
    private String testcaseId;
    private Long severityId;
    private Long defectStatusId;
    private Long priorityId;
    private Long typeId;
    private String receptorCount;
    private String attachment;
    private String assignTo;
    private String steps;
    private String releaseId;
    private String projectId;
    private String assignBy;
}
