package com.defect.defectTracker.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReleasesDTO {
    private Long id;               // Optional if you want to expose the ID for editing
    private String releaseId;      // Optional if you want to send/display a custom release identifier
    private String releaseName;
    private Date releaseDate;
    private String releaseType;
    private Long projectId;
}
