package com.defect.defectTracker.dto;

import lombok.Data;
import jakarta.transaction.Transactional;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class TestCaseDto {
    private Long id;
    private String description;
    private String steps;
    private String testCaseId;
    private String moduleId;
    private String projectId;
    private Long severityId;
    private String subModuleId;
    private String severityName;
    private Long typeId;
}
