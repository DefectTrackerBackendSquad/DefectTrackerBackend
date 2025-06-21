package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectStatusDto;
import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
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

        // 1. Validate input
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Defect status name cannot be null or empty.");
        }

        if (newName.trim().matches("\\d+")) {
            throw new IllegalArgumentException("Defect status name cannot contain only numbers.");
        }

        // 2. Check for existence
        DefectStatus existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DefectStatus with ID " + id + " not found."));

        // 3. Check for duplicate name (case-insensitive)
        Optional<DefectStatus> duplicate = repository.findByDefectStatusNameIgnoreCase(newName.trim());
        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            throw new IllegalArgumentException("Defect status name already exists.");
        }

        // 4. Update and save
        existing.setDefectStatusName(newName.trim());
        DefectStatus updated = repository.save(existing);
        return new DefectStatusDto(updated.getId(), updated.getDefectStatusName());
    }
}
