package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/defect")
@AllArgsConstructor
public class DefectController {
    @Autowired
    private  DefectService defectService;

    @PutMapping
    public ResponseEntity<StandardResponse> updateDefect(@RequestBody DefectDto defectDTO) {
        // Validate defect ID
        if (defectDTO.getDefectId() == null || defectDTO.getDefectId().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse(
                            "error",
                            "Defect ID is required",
                            null,
                            HttpStatus.BAD_REQUEST.value()
                    ));
        }

        // Validate description
        if (defectDTO.getDescription() == null || defectDTO.getDescription().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse(
                            "error",
                            "Description cannot be null or empty",
                            null,
                            HttpStatus.BAD_REQUEST.value()
                    ));
        }

        // Get existing defect
        Defect existing = defectService.getDefectByDefectId(defectDTO.getDefectId());
        if (existing == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponse(
                            "error",
                            "Defect not found",
                            null,
                            HttpStatus.NOT_FOUND.value()
                    ));
        }

        // Update fields
        existing.setDescription(defectDTO.getDescription());
        existing.setSteps(defectDTO.getSteps());

        try {
            Defect updated = defectService.updateDefect(existing);
            return ResponseEntity
                    .ok(new StandardResponse(
                            "success",
                            "Defect updated successfully",
                            updated,
                            HttpStatus.OK.value()
                    ));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse(
                            "error",
                            "Failed to update defect",
                            null,
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    ));
        }
    }
}
