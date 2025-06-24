package com.defect.defectTracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.defect.defectTracker.entity.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefectDto {
    private String id;
    private String defectTitle;
    private String descriptions;
    private String testcaseId;
    private String severity;
    private String defectStatus;
    private Long id;
    private String title;
    private String status;
    private Long severityId;
    private LocalDateTime createdDate;
    private Long projectId;
    private Long assignedToId;  // Developer who is assigned to fix
    private Long assignedById;  // Person who assigned the defect
    private Long defectStatusId;
    private Long priorityId;
    private Long typeId;
    private Integer reOpenCount;
    private String steps;
    private Long releaseTestCaseId;

    private String defectId;
    private String description;

    private String attachment;

    private ReleaseTestCase releaseTestCase;
    private User assignedBy;
    private User assignedTo;
    private Severity severity;
    private DefectStatus defectStatus;
    private Project project;
    private Priority priority;
    private Type defectType;

    private String projectName;

    private String assignName;

}
