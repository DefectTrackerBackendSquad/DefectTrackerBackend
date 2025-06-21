package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefectService {
    private final DefectRepo defectRepository;

    public List<Defect> getDefectsByProjectId(String projectId) {
        return defectRepository.findByProject_ProjectId(projectId);
    }
}
