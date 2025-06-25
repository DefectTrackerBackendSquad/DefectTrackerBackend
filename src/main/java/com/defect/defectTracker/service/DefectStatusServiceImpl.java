package com.defect.defectTracker.service;

import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import com.defect.defectTracker.dto.DefectStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.defect.defectTracker.util.StandardResponse;

import java.util.List;
import java.util.Optional;

@Service
public  class DefectStatusServiceImpl implements DefectStatusService {

    Logger logger = LoggerFactory.getLogger(DefectStatusServiceImpl.class);

    @Autowired
    private DefectStatusRepo defectStatusRepo;

    public DefectStatusDto updateDefectStatus(Long id, DefectStatusDto dto) {
        String newName = dto.getDefectStatusName();


        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Defect status name cannot be null or empty.");
        }

        if (newName.trim().matches("\\d+")) {
            throw new IllegalArgumentException("Defect status name cannot contain only numbers.");
        }
        if (!newName.trim().matches("^[a-zA-Z ]+$")) {
            throw new IllegalArgumentException("Defect status name must only contain letters and spaces (no symbols).");
        }


        DefectStatus existing = defectStatusRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DefectStatus with ID " + id + " not found."));


        Optional<DefectStatus> duplicate = defectStatusRepo.findByDefectStatusNameIgnoreCase(newName.trim());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            throw new IllegalArgumentException("Defect status name already exists.");
        }


        existing.setDefectStatusName(newName.trim());
        DefectStatus updated = defectStatusRepo.save(existing);

        return new DefectStatusDto(updated.getId(), updated.getDefectStatusName());
    }

    public StandardResponse deleteDefectStatus(Long defectStatusId) {
        Optional<DefectStatus> defectStatusOptional = defectStatusRepo.findById(defectStatusId);
        if (defectStatusOptional.isPresent()) {
            try {
                defectStatusRepo.deleteById(defectStatusId);
                return new StandardResponse("success", "Deleted successfully.", null, 2001);
            } catch (Exception e) {
                return new StandardResponse("failure", "Delete Failed.", null, 4001);
            }
        } else {
            return new StandardResponse("failure", "Id not exist.", null, 10009);
        }
    }

    @Override
    public DefectStatusDto createDefectStatus(DefectStatusDto defectStatusDto) {
        DefectStatus defectStatus = new DefectStatus();
        defectStatus.setDefectStatusName(defectStatusDto.getDefectStatusName());
        defectStatusRepo.save(defectStatus);
        return defectStatusDto;
    }

    @Override
    public List<DefectStatusDto> getDefectStatuses(){
        List<DefectStatus> defectStatuses = defectStatusRepo.findAll();
        List<DefectStatusDto> defectStatusDtos = defectStatuses.stream()
                .map(ds -> new DefectStatusDto(ds.getId(), ds.getDefectStatusName()))
                .toList();
        logger.info(String.valueOf(defectStatuses.size()));
        logger.info(String.valueOf(defectStatusDtos.size()));
        return defectStatusDtos;
    }
}

