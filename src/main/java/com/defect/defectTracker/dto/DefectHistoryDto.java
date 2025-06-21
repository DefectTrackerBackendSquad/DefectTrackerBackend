package com.defect.defectTracker.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class DefectHistoryDto {
    private Long id;
    private Date defectDate;
    private Time defectTime;
    private String previousStatus;
    private String assignedTo;
    private String assignedBy;
    private Long releaseId;
    private String defectStatus;
    private Long defectId;
}
