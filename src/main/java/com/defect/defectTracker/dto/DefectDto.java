package com.defect.defectTracker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.defect.defectTracker.entity.Defect;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefectDTO {
    private Long id; // DB Primary Key

    private String defectId;              // e.g., "DF00001"
    private String assignedBy;            // e.g., "US0001"
    private String assignedTo;            // e.g., "US0002"
    private String defectStatusId;        // e.g., "1"
    private String typeId;                // e.g., "1"
    private String priorityId;            // e.g., "1"
    private String projectId;             // e.g., "PR0001"
    private String releaseTestCaseId;     // e.g., "RET0001"
    private String severityId;            // e.g., "1"

    // Required Fields
//    @NotBlank(message = "Title is required")
    private String title;

//    @NotBlank(message = "Description is required")
    private String description;

//    @NotBlank(message = "Steps are required")
    private String steps;

    private int reOpenCount;

    private String attachment;

    // Output/Response fields (Names)
    private String assignedByName;
    private String assignedToName;
    private String defectStatusName;
    private String typeName;
    private String priorityName;
    private String projectName;
    private String releaseTestCaseName;
    private String severityName;

    // Constructor for converting Entity → DTO
    public DefectDTO(Defect defect) {
        this.id = defect.getId();
        this.defectId = defect.getDefectId();
        this.title = defect.getTitle();
        this.description = defect.getDescription();
        this.steps = defect.getSteps();
        this.reOpenCount = defect.getReOpenCount();
        this.attachment = defect.getAttachment() != null ? defect.getAttachment().toString() : null;
    }

    // Optional copy constructor
    public DefectDTO(DefectDTO defectDTO) {
        this.id = defectDTO.getId();
        this.defectId = defectDTO.getDefectId();
        this.title = defectDTO.getTitle();
        this.description = defectDTO.getDescription();
        this.steps = defectDTO.getSteps();
        this.reOpenCount = defectDTO.getReOpenCount();
        this.attachment = defectDTO.getAttachment();
        this.assignedBy = defectDTO.getAssignedBy();
        this.assignedTo = defectDTO.getAssignedTo();
        this.defectStatusId = defectDTO.getDefectStatusId();
        this.typeId = defectDTO.getTypeId();
        this.priorityId = defectDTO.getPriorityId();
        this.projectId = defectDTO.getProjectId();
        this.releaseTestCaseId = defectDTO.getReleaseTestCaseId();
        this.severityId = defectDTO.getSeverityId();
        this.assignedByName = defectDTO.getAssignedByName();
        this.assignedToName = defectDTO.getAssignedToName();
        this.defectStatusName = defectDTO.getDefectStatusName();
        this.typeName = defectDTO.getTypeName();
        this.priorityName = defectDTO.getPriorityName();
        this.projectName = defectDTO.getProjectName();
        this.releaseTestCaseName = defectDTO.getReleaseTestCaseName();
        this.severityName = defectDTO.getSeverityName();
    }
}