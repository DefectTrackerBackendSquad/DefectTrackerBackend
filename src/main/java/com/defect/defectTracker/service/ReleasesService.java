package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseDTO;
import com.defect.defectTracker.entity.Releases;

import java.util.Date;
import java.util.List;

public interface ReleasesService {
    Releases createRelease(Releases releases, String projectId);

    // Only declare the method signature (no implementation)
    List<ReleaseDTO> searchReleases(
            String releaseId,
            String releaseName,
            String releaseType,
            Date releaseDate,
            Long projectId
    );

    ReleaseDTO.ReleaseResponse getReleaseByReleaseId(String releaseId);
    List<ReleaseDTO> getReleasesByProjectId(String projectId);

}