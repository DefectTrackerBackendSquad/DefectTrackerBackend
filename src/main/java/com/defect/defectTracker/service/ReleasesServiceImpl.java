package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleasesDto;
import com.defect.defectTracker.dto.ReleasesDto.ReleaseResponse;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.ReleasesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReleasesServiceImpl implements ReleasesService {
    @Autowired
    private ReleasesRepo releasesRepository;

    @Override
    public ReleaseResponse getReleaseByReleaseId(String releaseId) {
        Releases release = releasesRepository.findByReleaseId(releaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));

        return mapToReleaseResponse(release);
    }

    private ReleasesDto.ReleaseResponse mapToReleaseResponse(Releases release) {
        return new ReleasesDto.ReleaseResponse(
                release.getReleaseId(),
                release.getReleaseName(),
                release.getProject().getProjectId(), // Only returning projectId
                String.valueOf(release.getReleaseDate()),
                release.getReleaseType()
        );
    }

}
