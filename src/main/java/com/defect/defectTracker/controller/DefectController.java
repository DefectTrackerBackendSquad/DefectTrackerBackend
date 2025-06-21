package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.service.DefectService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defect")
@AllArgsConstructor
public class DefectController {

    private final DefectService defectService;  // Injected service layer dependency

    @PutMapping
    public ResponseEntity<SimpleResponse> updateDefect(@RequestBody DefectDto defectDTO) {
        // Validate that the defect ID is provided
        if (defectDTO.getDefectId() == null || defectDTO.getDefectId().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(new SimpleResponse(
                            "error",
                            "4001",
                            "Defect ID is required."
                    ));
        }

        // Attempt to retrieve existing defect from database via service
        Defect existing = defectService.getDefectByDefectId(defectDTO.getDefectId());
        if (existing == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new SimpleResponse(
                            "error",
                            "4002",
                            "Defect not found."
                    ));
        }

        // Update mutable fields on the existing entity using data from DTO
        existing.setDescription(defectDTO.getDescription());
        existing.setSteps(defectDTO.getSteps());
        // Map other updatable fields here as needed (e.g., status, priority)

        // Call service to persist changes
        Defect updated = defectService.updateDefect(existing);
        if (updated == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new SimpleResponse(
                            "error",
                            "5001",
                            "Update failed."
                    ));
        }

        // Return successful response
        return ResponseEntity
                .ok(new SimpleResponse(
                        "success",
                        "2001",
                        "Updated successfully."
                ));
    }

    // DTO for uniform response, using Lombok to generate getters, setters & constructors
    @Data @NoArgsConstructor @AllArgsConstructor
    private static class SimpleResponse {
        private String status;
        private String statusCode;
        private String message;
    }
}
