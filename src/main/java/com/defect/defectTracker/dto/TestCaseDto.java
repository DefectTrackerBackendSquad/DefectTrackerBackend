package com.defect.defectTracker.dto;

import jakarta.transaction.Transactional;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional

public class TestCaseDto {
    private Long id;
    private String testCaseId;
    private String description;
    private String steps;

    private Long subModuleId;
    private Long moduleId;
    private Long projectId;
    private Long severityId;
    private Long typeId;
}
