package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto {
    private Long id;
    private String testCaseId;
    private String description;
    private String steps;
    private Long subModuleId;
    private Long moduleId;
}
