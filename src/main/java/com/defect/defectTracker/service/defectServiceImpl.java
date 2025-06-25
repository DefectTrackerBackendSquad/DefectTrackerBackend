package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.dto.DefectFilterResponseDTO;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Lombok generates constructor
@Service
@RequiredArgsConstructor // Lombok generates constructor
@Transactional
public class defectServiceImpl implements DefectService {
    Logger logger = LoggerFactory.getLogger(defectServiceImpl.class);

    @Autowired
    private DefectRepo defectRepo;

    @Autowired private UserRepo userRepo;
    @Autowired private ProjectRepo projectRepo;
    @Autowired private SeverityRepo severityRepo;
    @Autowired private PriorityRepo priorityRepo;
    @Autowired private TypeRepo typeRepo;
    @Autowired private ReleaseTestCaseRepo releaseTestCaseRepo;
    @Autowired private DefectStatusRepo defectStatusRepo;

    @Override
    public void createDefect(DefectDto dto) {

        Logger logger = LoggerFactory.getLogger(defectServiceImpl.class);
        Defect defect = new Defect();

        // Set direct fields
        defect.setDefectId(dto.getDefectId()); // Set from DTO
        defect.setDescription(dto.getDescriptions()); // Fixed: now uses correct field
        defect.setSteps(dto.getSteps());
        defect.setAttachment(dto.getAttachment());
        defect.setReOpenCount(dto.getReOpenCount() != null ? dto.getReOpenCount().intValue() : 0);
        // Use value from DTO if available

        // Set relationships
        defect.setReleaseTestCase(
                releaseTestCaseRepo.findByReleaseTestCaseId(String.valueOf(dto.getReleaseTestCaseId()))
                        .orElseThrow(() -> new ResourceNotFoundException("ReleaseTestCase not found"))
        );
        logger.info(String.valueOf(defect.getAssignedBy()));

        defect.setAssignedBy(
                userRepo.findById(String.valueOf(dto.getAssignedById()))
                        .orElseThrow(() -> new ResourceNotFoundException("AssignedBy user not found"))
        );
        logger.info(String.valueOf(defect.getAssignedBy()));

        defect.setAssignedTo(
                userRepo.findById(String.valueOf(dto.getAssignedToId()))
                        .orElseThrow(() -> new ResourceNotFoundException("AssignedTo user not found"))
        );
        logger.info(String.valueOf(defect.getAssignedBy()));

        defect.setSeverity(
                severityRepo.findById(dto.getSeverityId())
                        .orElseThrow(() -> new ResourceNotFoundException("Severity not found"))
        );
        logger.info(String.valueOf(defect.getAssignedBy()));

        defect.setDefectStatus(
                defectStatusRepo.findById(dto.getDefectStatusId())
                        .orElseThrow(() -> new ResourceNotFoundException("DefectStatus not found"))
        );
        logger.info(String.valueOf(defect.getDefectStatus()));

        defect.setProject(
                projectRepo.findByProjectId(String.valueOf(dto.getProjectId()))
                        .orElseThrow(() -> new ResourceNotFoundException("Project not found"))
        );
        logger.info(String.valueOf(defect.getProject()));
        logger.info(("Project not found"));

        defect.setPriority(
                priorityRepo.findById(dto.getPriorityId())
                        .orElseThrow(() -> new ResourceNotFoundException("Priority not found"))
        );
        logger.info(String.valueOf(defect.getPriority()));

        defect.setDefectType(
                typeRepo.findById(dto.getTypeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Type not found"))
        );
        logger.info(String.valueOf(defect.getDefectType()));

        // Save to DB
        defectRepo.save(defect);
    }


    @Override
    public List<DefectFilterResponseDTO> filterDefects(String projectId, Long statusId, Long priorityId, Long severityId, Long typeId) {
        List<Defect> defects = defectRepo.filterDefects(projectId, statusId, priorityId, severityId, typeId);


        logger.info(projectId,statusId,priorityId,severityId,typeId);
        logger.info(String.valueOf(defects.size()));
        return defects.stream().map(d -> {
            DefectFilterResponseDTO dto = new DefectFilterResponseDTO();
            dto.setDefectId(d.getId());
            dto.setDescriptions(d.getDescription());
            dto.setTestCaseId(d.getReleaseTestCase() != null && d.getReleaseTestCase().getTestCase() != null ?
                    d.getReleaseTestCase().getTestCase().getDescription() : null);
            dto.setSeverity(d.getSeverity() != null ? d.getSeverity().getSeverityName() : null);
            dto.setPriority(d.getPriority() != null ? d.getPriority().getPriority() : null);
            dto.setType(d.getDefectType() != null ? d.getDefectType().getTypeName() : null);
            dto.setAssignBy(d.getAssignedBy() != null ? d.getAssignedBy().getFirstName() : null);
            dto.setAssignTo(d.getAssignedTo() != null ? d.getAssignedTo().getUserId() : null);
            dto.setProject(d.getProject() != null ? d.getProject().getProjectName() : null);
            dto.setStatus(d.getDefectStatus() != null ? d.getDefectStatus().getDefectStatusName() : null);
            dto.setReopenCount(d.getReOpenCount());
            dto.setAttachment(d.getAttachment());
            dto.setSteps(d.getSteps());
            dto.setReleaseTestCase(d.getReleaseTestCase() != null ? d.getReleaseTestCase().getReleases().getReleaseName() : null);
            logger.info(dto.getDescriptions());
            return dto;
        }).toList();}

    @Override
    public List<Defect> getDefectsByAssignee(Long userId) {
        return defectRepo.findByAssignedById(userId);
    }


    @Override
    public Defect getDefectByDefectId(String id) {
        DefectDto defectDto = new DefectDto();
        Defect defect = defectRepo.findByDefectId(id);
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
        return defectRepo.save(defect);
    }
}