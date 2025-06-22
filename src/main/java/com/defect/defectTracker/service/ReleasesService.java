// src/main/java/com/example/defectTracker/service/ReleaseService.java
package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleasesDto;

public interface ReleasesService {
    ReleasesDto.ReleaseResponse getReleaseByReleaseId(String releaseId);
}