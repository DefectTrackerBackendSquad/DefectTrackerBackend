// src/main/java/com/example/defectTracker/service/ReleaseService.java
package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.responseDto.ReleaseResponse;

public interface ReleasesService {
    ReleaseResponse getReleaseByReleaseId(String releaseId);
}