// TestCaseResponseDto.java
package com.defect.defectTracker.dto;

import lombok.Data;

@Data
public class TestCaseDto {
    private Long id;
    private String description;
    private String steps;
    private String testCaseId;
    private String moduleId;
    private String projectId;
    private Long severityId;
    private String subModuleId;
    private Long typeId;
}
