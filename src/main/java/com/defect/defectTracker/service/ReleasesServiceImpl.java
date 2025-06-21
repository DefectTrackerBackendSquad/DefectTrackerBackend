package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.repository.ReleasesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReleasesServiceImpl implements ReleasesService {

    @Autowired
    private ReleasesRepo releasesRepository;

    // 🔍 Search for releases with optional filters
    @Override
    public List<Releases> searchReleases(String releaseName, String releaseType, Date releaseDate, Long projectId) {
        return releasesRepository.searchReleases(releaseName, releaseType, releaseDate, projectId);
    }
}