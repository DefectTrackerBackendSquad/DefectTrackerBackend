package com.defect.defectTracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectDto {
    private String id;
    private String defectTitle;
    private String descriptions;
    private String testcaseId;
    private String severity;
    private String defectStatus;
}
