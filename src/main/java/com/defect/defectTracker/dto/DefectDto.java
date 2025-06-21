package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.entity.Defect;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefectDto {

    private Long id;
    private String defectId;
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
    private String projectName;
    private String status;
    private String assignName;
    private String message;
    private String testcaseId;
    private Long severityId;
    private Long defectStatusId;
    private Long priorityId;
    private Long typeId;
    private String receptorCount;
    private String assignTo;
    private String releaseId;
    private String projectId;
    private String assignBy;

    public DefectDto(Defect defect) {
        if (defect == null) return;
        this.id = defect.getId();
        this.defectId = defect.getDefectId();
        this.description = defect.getDescription();
        this.steps = defect.getSteps();
        this.reOpenCount = defect.getReOpenCount();
        this.assignName = (defect.getAssignedBy() != null) ? defect.getAssignedBy().getFirstName() + " " + defect.getAssignedBy().getLastName() : null;
        this.projectName = (defect.getProject() != null) ? defect.getProject().getProjectName() : null;
        this.status = (defect.getDefectStatus() != null) ? defect.getDefectStatus().getDefectStatusName() : null;
    }

}
