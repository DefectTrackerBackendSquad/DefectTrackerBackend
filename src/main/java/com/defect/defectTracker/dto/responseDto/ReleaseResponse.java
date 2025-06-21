package com.defect.defectTracker.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseResponse {
    private String releaseId;
    private String releaseName;
    private String projectId;      // 💡 Only show projectId, not full object
    private String releaseDate;
    private String releaseType;
}
