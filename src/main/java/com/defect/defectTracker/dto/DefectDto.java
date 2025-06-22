package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefectDto {
    private Long id;
    private String defectId;
    private String description;
    private int reOpenCount;
    private String attachment;
    private String steps;

    // Replace entity references with IDs
    private Long releaseTestCaseId;
    private Long assignedById;
    private Long assignedToId;
    private Long severityId;
    private Long defectStatusId;
    private Long projectId;
    private Long priorityId;
    private Long typeId;

    // Other fields remain the same
    private String projectName;
    private String status;
    private String assignName;
    // ... rest of your fields
}