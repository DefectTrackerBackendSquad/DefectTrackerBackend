// DefectController.java
package com.example.defectTracker.controller;

import com.example.defectTracker.Dto.DefectRequest;
import com.example.defectTracker.Dto.DefectResponse;
import com.example.defectTracker.entity.Defect;
import com.example.defectTracker.service.DefectService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defect")
public class DefectController {

    private final DefectService defectService;

    public DefectController(DefectService defectService) {
        this.defectService = defectService;
    }

    @PutMapping
    public ResponseEntity<DefectResponse> updateDefect(@RequestBody DefectRequest defectRequest) {
        // Validate mandatory fields
        if (defectRequest.getDefectId() == null || defectRequest.getDefectId().isEmpty()) {
            return badRequest("Defect ID is required");
        }

        Defect existingDefect = defectService.getDefectByDefectId(defectRequest.getDefectId());
        if (existingDefect == null) {
            return badRequest("Defect not found");
        }

        // Update defect properties
        BeanUtils.copyProperties(defectRequest, existingDefect, "id", "defectId");

        Defect updatedDefect = defectService.updateDefect(existingDefect);
        if (updatedDefect == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DefectResponse("Update failed"));
        }

        return ResponseEntity.ok(new DefectResponse("Updated successfully"));
    }

    private ResponseEntity<DefectResponse> badRequest(String message) {
        return ResponseEntity.badRequest().body(new DefectResponse(message));
    }
}