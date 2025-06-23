package com.defect.defectTracker.service;


import com.defect.defectTracker.repository.ProjectRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepo projectRepo;


    @Override
    public String getByProjectId(String projectId) {
        return projectId;
    }
}
