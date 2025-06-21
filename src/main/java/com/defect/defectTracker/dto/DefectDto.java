package com.defect.defectTracker.dto;

import com.defect.defectTracker.entity.*;

public class DefectDto {
    private String description;
    private int reOpenCount;
    private String attachment;
    private String steps;
    private ReleaseTestCase releaseTestCase;
    private User assignedBy;
    private User assignedTo;
    private Severity severity;
    private DefectStatus defectStatus;
    private Project project;
    private Priority priority;
    private Type defectType;
}
