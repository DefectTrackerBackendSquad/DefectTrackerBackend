package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/defect")
@RequiredArgsConstructor
public class DefectController {
    private final DefectService defectService;

    @GetMapping("/filter")
    public ResponseEntity<Object> getDefectsByFlexibleFilters(
            @RequestParam(required = false) Long statusId,
            @RequestParam(required = false) Long severityId,
            @RequestParam(required = false) Long priorityId,
            @RequestParam(required = false) Long typeId,
            @RequestParam Long projectId) {
        try {
            StandardResponse response = defectService.getDefectsByFlexibleFilters(statusId, severityId, priorityId, typeId, projectId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new java.util.HashMap<String, Object>() {{
                    put("status", "Failure");
                    put("message", "Bad Request");
                    put("statusCode", 4000);
                }}
            );
        }
    }

}
