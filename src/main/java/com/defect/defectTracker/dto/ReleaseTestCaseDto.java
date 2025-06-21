package com.defect.defectTracker.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class ReleaseTestCaseDto {
    private Long id;
    private String releaseTestCaseId;
    private Date testDate;
    private Time testTime;
    private String testCaseStatus;
    private String testCaseId;
    private String releaseId;
}
