package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Project;
import com.defect.defectTracker.entity.Releases;
import com.defect.defectTracker.repository.ProjectRepo;
import com.defect.defectTracker.repository.ReleasesRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

    private static final Logger logger = LoggerFactory.getLogger(ReleaseServiceImpl.class);

    @Autowired
    private ReleasesRepo releaseRepository;

    @Autowired
    private ProjectRepo projectRepository;

    @Override
    public Releases createRelease(Releases releases, String projectId) {
        logger.info("Creating release for project ID: {}", projectId);

        // 1. Validate project exists
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));

        // 2. Validate release name
        if (releases.getReleaseName() == null || releases.getReleaseName().isEmpty()) {
            throw new RuntimeException("Release name cannot be empty");
        }

        // 3. Save release (ID will be auto-generated)
        releases.setProject(project);
        Releases savedRelease = releaseRepository.save(releases);

        // 4. Format releaseId as RE0001, RE0002, etc.
        String formattedReleaseId = String.format("RE%04d", savedRelease.getId());
        savedRelease.setReleaseId(formattedReleaseId);

        // 5. Update to store formatted ID
        releaseRepository.save(savedRelease);
        logger.info("Successfully created release with ID: {}", savedRelease.getReleaseId());

        return savedRelease;
    }
}