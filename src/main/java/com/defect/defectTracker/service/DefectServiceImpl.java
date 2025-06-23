package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import com.defect.defectTracker.utils.StandardResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// Lombok generates constructor
@Service
@RequiredArgsConstructor // Lombok generates constructor
@Transactional
public class DefectServiceImpl implements DefectService {
    Logger logger = LoggerFactory.getLogger(DefectServiceImpl.class);

    @Autowired
    private DefectRepo defectRepository;

    @Override
    public StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId) {
        List<Defect> defects = defectRepository.findWithAllFilters(statusId, severityId, priorityId, typeId, projectId);
        return buildResponse(defects);
    }

    @Override
    public StandardResponse buildResponse(List<Defect> defects) {
        Logger logger = LoggerFactory.getLogger(DefectServiceImpl.class);
        List<DefectDto> defectDTOs = defects.stream().map(this::convertToDefectDto).toList();
        StandardResponse response = new StandardResponse();
        response.setStatus("success");
        response.setStatusCode(2000);
        response.setMessage("retrieved successfully");
        response.setData(Map.of("defects", defectDTOs));
        logger.info("Retrieved {} defects", defectDTOs.size());
        return response;
    }

    private DefectDto convertToDefectDto(Defect defect) {
        DefectDto dto = new DefectDto();
        dto.setId(defect.getId());
        dto.setTitle(defect.getDescription());
        dto.setDescription(defect.getDescription());
        dto.setStatus(defect.getDefectStatus() != null ? defect.getDefectStatus().getDefectStatusName() : null);
        dto.setSeverityId(defect.getSeverity() != null ? defect.getSeverity().getId() : null);
        dto.setProjectId(defect.getProject() != null ? (defect.getProject().getId()) : null);
        dto.setAssignedToId(defect.getAssignedTo() != null ? defect.getAssignedTo().getId() : null);
        dto.setAssignedById(defect.getAssignedBy() != null ? defect.getAssignedBy().getId() : null);
        dto.setDefectStatusId(defect.getDefectStatus() != null ? defect.getDefectStatus().getId() : null);
        dto.setPriorityId(defect.getPriority() != null ? defect.getPriority().getId() : null);
        dto.setTypeId(defect.getDefectType() != null ? defect.getDefectType().getId() : null);
        dto.setReOpenCount(defect.getReOpenCount());
        dto.setSteps(defect.getSteps());
        dto.setReleaseTestCaseId(defect.getReleaseTestCase() != null ? defect.getReleaseTestCase().getId() : null);
        return dto;
    }

    @Override
    public List<Defect> getDefectsByAssignee(Long userId) {
        return defectRepository.findByAssignedById(userId);
    }


    @Override
    public Defect getDefectByDefectId(String id) {
        DefectDto defectDto = new DefectDto();
        Defect defect = defectRepository.findByDefectId(id);
        logger.info("Fetching defect with ID: {}", id);
        if (defect != null) {
            //logger.info("Defect found: {}", defect.get().getDefectId());
            //BeanUtils.copyProperties(defectDto, defect.get());
            return defect;
        } else {
            return null;
        }
    }

    @Override
    public Defect updateDefect(Defect defect) {
        return defectRepository.save(defect);
    }
}