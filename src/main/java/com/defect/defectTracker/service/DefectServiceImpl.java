package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor // Lombok generates constructor
public class DefectServiceImpl implements DefectService {

    private final DefectRepo defectRepository;

    @Override
    public StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId) {
        List<Defect> defects = defectRepository.findWithAllFilters(statusId, severityId, priorityId, typeId, projectId);
        return buildResponse(defects);
    }

    private StandardResponse buildResponse(List<Defect> defects) {
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
        dto.setSeverity(defect.getSeverity() != null ? defect.getSeverity().getSeverityName() : null);
        dto.setCreatedDate(defect.getCreatedDate());
        dto.setProjectId(defect.getProject() != null ? (defect.getProject().getId()) : null);
        dto.setAssignedTo(defect.getAssignedTo() != null ? defect.getAssignedTo().getId() : null);
        dto.setAssignedBy(defect.getAssignedBy() != null ? defect.getAssignedBy().getId() : null);
        dto.setDefectStatusId(defect.getDefectStatus() != null ? defect.getDefectStatus().getId() : null);
        dto.setPriorityId(defect.getPriority() != null ? defect.getPriority().getId() : null);
        dto.setTypeId(defect.getDefectType() != null ? defect.getDefectType().getId() : null);
        dto.setReOpenCount(defect.getReOpenCount());
        dto.setSteps(defect.getSteps());
        dto.setReleaseTestCaseId(defect.getReleaseTestCase() != null ? defect.getReleaseTestCase().getId() : null);
        return dto;
    }
}

