package com.defect.defectTracker.dto;

import java.sql.Time;
import java.util.Date;
import lombok.Data;


@Data

public class ReleaseTestCaseDto {
    private Long releaseTestCaseId;
    private String testCaseId;
    private String testCaseDescription;
    private String testSteps;
    private Date testDate;
    private Time testTime;
    private String testCaseStatus;

    private String releaseId;

}
