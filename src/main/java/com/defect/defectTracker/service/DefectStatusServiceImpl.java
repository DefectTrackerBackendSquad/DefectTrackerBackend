package com.defect.defectTracker.service;


import com.defect.defectTracker.dto.DefectStatusDto;
import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import com.defect.defectTracker.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefectStatusServiceImpl implements DefectStatusService {

    @Autowired
    private DefectStatusRepo repository;

    @Override
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


            DefectStatus existing = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("DefectStatus with ID " + id + " not found."));


            Optional<DefectStatus> duplicate = repository.findByDefectStatusNameIgnoreCase(newName.trim());
            if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
                throw new IllegalArgumentException("Defect status name already exists.");
            }


            existing.setDefectStatusName(newName.trim());
            DefectStatus updated = repository.save(existing);

            return new DefectStatusDto(updated.getId(), updated.getDefectStatusName());
        }



    }
    private DefectStatusRepo defectStatusRepo;

    @Override
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
}
