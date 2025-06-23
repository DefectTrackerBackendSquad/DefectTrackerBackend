package com.defect.defectTracker.service;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.repository.DefectRepo;
import com.defect.defectTracker.utils.StandardResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DefectServiceImpl implements DefectService {

    private static final String DEFECT_ID_PREFIX = "DF";
    @Autowired
    public DefectRepo defectRepo;

    @Override
    public StandardResponse createDefect(DefectDto dto) {
        // Check if defectId exists already
        String defectId = dto.getDefectId();
        if (defectId != null && !defectId.isBlank()) {
            if (defectRepo.findByDefectId(defectId) != null) {
                // Return error response instead of throwing exception
                return new StandardResponse(
                        "error",
                        "Defect ID already exists",
                        null, HttpStatus.OK.value()   // 200 OK instead of 400 Bad Request
                );
            }
        } else {
            // Generate new defectId if not provided
            Long count = defectRepo.countByDefectIdStartingWith(DEFECT_ID_PREFIX);
            defectId = String.format("%s%05d", DEFECT_ID_PREFIX, count + 1);
        }

        Defect defect = new Defect();
        defect.setDefectId(defectId);
        defect.setTitle(dto.getTitle());
        defect.setDescription(dto.getDescription());
        defect.setSteps(dto.getSteps());
        defect.setReOpenCount(dto.getReOpenCount());
        defect.setAttachment(dto.getAttachment());

        // Note: Add relationship setters here if needed

        Defect savedDefect = defectRepo.save(defect);

        return new StandardResponse(
                "success",
                "Defect created successfully",
                new DefectDto(savedDefect),
                HttpStatus.CREATED.value()
        );
    }

    @Override
    public List<DefectDto> getDefectsByAssignee(String userId) {
        return defectRepo.findByAssignedToId(userId)
                .stream()
                .map(DefectDto::new)
                .collect(Collectors.toList());
    }
}