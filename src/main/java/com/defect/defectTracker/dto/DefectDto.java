package com.defect.defectTracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DefectDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long severityId;
    private String severity;
    private LocalDateTime createdDate;
    private Long projectId;
    private Long assignedTo;  // Developer who is assigned to fix
    private Long assignedBy;  // Person who assigned the defect
    private Long defectStatusId;
    private Long priorityId;
    private Long typeId;
    private Integer reOpenCount;
    private String steps;
    private Long releaseTestCaseId;
}

