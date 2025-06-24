package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefectServiceImpl implements DefectService {

    private final Logger logger = LoggerFactory.getLogger(DefectServiceImpl.class);
    private final DefectRepo defectRepo;

    @Override
    public StandardResponse getDefectsByFlexibleFilters(Long statusId, Long severityId, Long priorityId, Long typeId, Long projectId) {
        List<Defect> defects = defectRepo.findWithAllFilters(statusId, severityId, priorityId, typeId, projectId);
        return buildResponse(defects);
    }

    @Override
    public Defect getDefectByDefectId(String defectId) {
        logger.info("Fetching defect with ID: {}", defectId);
        Defect defect = defectRepo.findByDefectId(defectId);
        if (defect == null) {
            logger.warn("Defect with ID {} not found", defectId);
            throw new HttpMessageNotWritableException("Defect not found");
        }
        return defect;
    }

    @Override
    public List<Defect> getDefectsByAssignee(Long userId) {
        return defectRepo.findByAssignedById(userId);
    }

    @Override
    public DefectDto getDefectByTestcaseId(String testcaseId) {
        Optional<Defect> optionalDefect = defectRepo.findByTestcaseId(testcaseId);
        Defect defect = optionalDefect.orElseThrow(() -> {
            logger.warn("Test case ID {} not found", testcaseId);
            return new HttpMessageNotWritableException("Test Case not found");
        });

        return convertToDefectDto(defect);
    }

    @Override
    public Defect updateDefect(Defect defect) {
        return defectRepo.save(defect);
    }

    private StandardResponse buildResponse(List<Defect> defects) {
        List<DefectDto> defectDTOs = defects.stream().map(this::convertToDefectDto).toList();
        StandardResponse response = StandardResponse.builder()
                .status("success")
                .statusCode(2000)
                .message("retrieved successfully")
                .data(Map.of("defects", defectDTOs))
                .build();
        logger.info("Retrieved {} defects", defectDTOs.size());
        return response;
    }

    private DefectDto convertToDefectDto(Defect defect) {
        DefectDto dto = new DefectDto();
        dto.setId(defect.getDefectId());
        dto.setTitle(defect.getDescription());
        dto.setDescription(defect.getDescription());
        dto.setCreatedDate(defect.getCreatedDate());

        if (defect.getDefectStatus() != null) {
            dto.setStatus(defect.getDefectStatus().getDefectStatusName());
            dto.setDefectStatusId(defect.getDefectStatus().getId());
        }

        if (defect.getSeverity() != null) {
            dto.setSeverity(defect.getSeverity().getSeverityName());
            dto.setSeverityId(defect.getSeverity().getId());
        }

        if (defect.getPriority() != null) {
            dto.setPriorityId(defect.getPriority().getId());
        }

        if (defect.getDefectType() != null) {
            dto.setTypeId(defect.getDefectType().getId());
        }

        if (defect.getProject() != null) {
            dto.setProjectId(defect.getProject().getId());
        }

        if (defect.getAssignedTo() != null) {
            dto.setAssignedToId(defect.getAssignedTo().getId());
        }

        if (defect.getAssignedBy() != null) {
            dto.setAssignedById(defect.getAssignedBy().getId());
        }

        dto.setReOpenCount(defect.getReOpenCount());
        dto.setSteps(defect.getSteps());

        if (defect.getReleaseTestCase() != null) {
            dto.setReleaseTestCaseId(defect.getReleaseTestCase().getId());
            dto.setTestcaseId(defect.getReleaseTestCase().getReleaseTestCaseId());
        }

        return dto;
    }
}
