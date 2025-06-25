package com.defect.defectTracker.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class ReleaseTestCaseDto {
    private String releaseTestCaseId;
    private String testCaseStatus;
    private Date testDate;
    private Time testTime;
    private Long releaseId;
    private Long testCaseId;
}