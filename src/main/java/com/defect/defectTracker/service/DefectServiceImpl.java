package com.defect.defectTracker.service;
import com.defect.defectTracker.exceptionHandler.ResourceNotFoundException;
import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.entity.*;
import com.defect.defectTracker.repository.DefectRepo;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

 // Lombok generates constructor
@Service
@RequiredArgsConstructor // Lombok generates constructor
public class DefectServiceImpl implements DefectService {
    Logger logger = LoggerFactory.getLogger(DefectServiceImpl.class);

    @Autowired
    private DefectRepo defectRepository;

    @Override
    public DefectDto getDefectByTestcaseId(String testcaseId) {
        Defect defect = defectRepo.findByReleaseTestCaseId(testcaseId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Case not found"));


        //Defect defect = defectRepo.findByReleaseTestCase_ReleaseTestCaseId(testcaseId)
               // .orElseThrow(() -> new ResourceNotFoundException("Test case not found"));
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
        dto.setId(defect.getDefectId());
        dto.setDefectTitle(defect.getDescription());
        dto.setDescriptions(defect.getDescription());
      //  dto.setTestcaseId(defect.getReleaseTestCase().getReleaseTestCaseId());
        dto.setSeverity(defect.getSeverity().getSeverityName());
        dto.setDefectStatus(defect.getDefectStatus().getDefectStatusName());

        dto.setId(defect.getId());
        dto.setTitle(defect.getDescription());
        dto.setDescription(defect.getDescription());
        dto.setStatus(defect.getDefectStatus() != null ? defect.getDefectStatus().getDefectStatusName() : null);
        dto.setSeverityId(defect.getSeverity() != null ? defect.getSeverity().getId() : null);
        dto.setSeverity(defect.getSeverity() != null ? defect.getSeverity() : null);
       // dto.setCreatedDate(defect.getcreatedDate());
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
    @Autowired
    private DefectRepo defectRepo;

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
