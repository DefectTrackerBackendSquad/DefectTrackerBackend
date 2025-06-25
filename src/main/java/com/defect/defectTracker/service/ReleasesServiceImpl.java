package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.ReleaseDTO;
import com.defect.defectTracker.entity.Project;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.ProjectRepo;
import com.defect.defectTracker.repository.ReleasesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReleasesServiceImpl implements ReleasesService {

    private static final Logger logger = LoggerFactory.getLogger(ReleasesServiceImpl.class);

    @Autowired
    private ReleasesRepo releaseRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @Override
    public List<ReleaseDTO> searchReleases(String releaseId, String releaseName, String releaseType,
                                           Date releaseDate, Long projectId) {
        logger.info("Searching releases with filters - releaseId: {}, releaseName: {}, releaseType: {}, releaseDate: {}, projectId: {}",
                releaseId, releaseName, releaseType, releaseDate, projectId);

        List<Releases> releasesList = releaseRepository.search(
                releaseId, releaseName, releaseType, releaseDate, projectId);

        return releasesList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReleaseDTO convertToDto(Releases release) {
        ReleaseDTO dto = new ReleaseDTO();
        dto.setId(release.getId());
        dto.setReleaseId(release.getReleaseId());
        dto.setReleaseName(release.getReleaseName());
        dto.setReleaseDate(release.getReleaseDate());
        dto.setReleaseType(release.getReleaseType());

        if (release.getProject() != null) {
            dto.setProjectId(release.getProject().getId());
        }

        return dto;
    }

    @Override
    public Releases createRelease(Releases releases, String projectId) {
        logger.info("Creating release for project ID: {}", projectId);

        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        validateRelease(releases);

        releases.setProject(project);
        Releases savedRelease = releaseRepository.save(releases);

        String formattedReleaseId = String.format("RE%04d", savedRelease.getId());
        savedRelease.setReleaseId(formattedReleaseId);

        return releaseRepository.save(savedRelease);
    }

    private void validateRelease(Releases release) {
        if (release.getReleaseName() == null || release.getReleaseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Release name cannot be empty");
        }

        if (release.getReleaseType() == null || release.getReleaseType().trim().isEmpty()) {
            throw new IllegalArgumentException("Release type cannot be empty");
        }

        if (release.getReleaseDate() == null) {
            throw new IllegalArgumentException("Release date cannot be null");
        }
    }

    @Override
    public ReleaseDTO.ReleaseResponse getReleaseByReleaseId(String releaseId) {
        Releases release = releaseRepository.findByReleaseId(releaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Release not found with id: " + releaseId));

        return mapToReleaseResponse(release);
    }

    private ReleaseDTO.ReleaseResponse mapToReleaseResponse(Releases release) {
        String formattedDate = null;
        if (release.getReleaseDate() != null) {
            formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(release.getReleaseDate());
        }

        Long projectId = release.getProject() != null ? release.getProject().getId() : null;

        return new ReleaseDTO.ReleaseResponse(
                release.getReleaseId(),
                release.getReleaseName(),
                projectId,
                formattedDate,
                release.getReleaseType()
        );
    }

    @Override
    public List<ReleaseDTO> getReleasesByProjectId(String projectId) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + projectId));

        List<Releases> releasesList = releaseRepository.findByProject(project);

        return releasesList.stream()
                .map(ReleaseDTO::fromEntity)
                .collect(Collectors.toList());
    }


}
