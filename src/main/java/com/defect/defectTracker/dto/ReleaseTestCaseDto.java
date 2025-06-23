package com.defect.defectTracker.dto;


import java.sql.Time;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseTestCaseDto {
    private Long id;
    private String releaseTestCaseId;
    private Date testDate;
    private Time testTime;
    private String testCaseStatus;
    private Long userId;
    private Long testCaseId;
    private Long releasesId;
}
