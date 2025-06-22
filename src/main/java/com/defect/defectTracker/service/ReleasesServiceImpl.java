// src/main/java/com/example/defectTracker/service/impl/ReleaseServiceImpl.java
package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.responseDto.ReleaseResponse;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.ReleasesRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReleasesServiceImpl implements ReleasesService {

    private final ReleasesRepo releasesRepository;

    @Override
    public ReleaseResponse getReleaseByReleaseId(String releaseId) {
        Releases release = releasesRepository.findByReleaseId(releaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));

        return mapToReleaseResponse(release);
    }

    private ReleaseResponse mapToReleaseResponse(Releases release) {
        ReleaseResponse response = new ReleaseResponse();
        response.setReleaseId(release.getReleaseId());
        response.setReleaseName(release.getReleaseName());
        response.setProjectId(release.getProject().getProjectId());
        response.setReleaseDate(String.valueOf(release.getReleasedate()));
        response.setReleaseType(release.getReleaseType());
        return response;
    }
}