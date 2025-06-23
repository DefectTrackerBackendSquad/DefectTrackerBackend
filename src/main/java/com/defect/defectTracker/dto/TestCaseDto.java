// TestCaseResponseDto.java
package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto {
    private Long id;
    //private Long id;
    private String testCaseId;
    private String description;
    private String steps;
    //private String testCaseId;
    private Long subModuleId;
    private Long moduleId;
    private Long severityId;
    private Long typeId;
    private Long projectId;




    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class testcasedto {
        private Long id;
        private String description;
        private String subModuleId;
        private Long severityId;
        private String steps;
        private Long typeId;
        private String moduleId;
        private String projectId;
        //private Long severityId;
        //private String subModuleId;
        //private Long typeId;
        private String testCaseId;

    }
}
