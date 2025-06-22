package com.defect.defectTracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectDto {
    private String id;
    private String defectId;
    private String defectTitle;
    private String descriptions;
    private String releaseTestCaseId;
    private String severity;
    private String defectStatus;
}
