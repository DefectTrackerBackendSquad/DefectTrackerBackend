package com.defect.defectTracker.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseDto {
    private Long id;
    private String description;
    private String subModuleId;
    private Long severityId;
    private String steps;
    private Long typeId;
    private String moduleId;
    private String projectId;
    private String testCaseId;

}
