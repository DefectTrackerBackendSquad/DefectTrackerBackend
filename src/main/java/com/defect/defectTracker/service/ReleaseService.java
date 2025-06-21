package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Releases;

public interface ReleaseService {
    Releases createRelease(Releases releases, String projectId);
}