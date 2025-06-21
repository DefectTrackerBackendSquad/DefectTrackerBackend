package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Releases;
import java.util.Date;
import java.util.List;

public interface ReleasesService {
    List<Releases> searchReleases(String releaseName, String releaseType, Date releaseDate, Long projectId);
}