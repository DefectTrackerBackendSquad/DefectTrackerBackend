package com.defect.defectTracker.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.defect.defectTracker.entity.Defect;
@AllArgsConstructor
@NoArgsConstructor
public class DefectDto {
  
    private Long id;
    private String defectId;
    private String description;
    private String steps;
    private int reOpenCount;
    private String assignedbyName;
    private String projectName;
    private String status;

    public DefectDto(Defect defect) {
        if (defect == null) return;
        this.id = defect.getId();
        this.defectId = defect.getDefectId();
        this.description = defect.getDescription();
        this.steps = defect.getSteps();
        this.reOpenCount = defect.getReOpenCount();
        this.assignedbyName = (defect.getAssignedBy() != null) ? defect.getAssignedBy().getFirstName() + " " + defect.getAssignedBy().getLastName() : null;
        this.projectName = (defect.getProject() != null) ? defect.getProject().getProjectName() : null;
        this.status = (defect.getDefectStatus() != null) ? defect.getDefectStatus().getDefectStatusName() : null;
    }
}
