package com.defect.defectTracker.dto;

import jakarta.transaction.Transactional;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class TestCaseDto {
    private Long id;
    private String testCaseId;
    private String description;
    private String steps;


    private String subModuleId;
    private String moduleId;
    private String projectId;
    private Long severityId;
    private String severityName;
    private Long typeId;

}
