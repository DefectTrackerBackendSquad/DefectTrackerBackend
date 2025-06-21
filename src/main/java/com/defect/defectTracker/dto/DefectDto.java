package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefectDto {
    private String id;
    private String defectTitle;
    private String descriptions;
    private String testcaseId;
    private String severity;
    private String defectStatus;
}
