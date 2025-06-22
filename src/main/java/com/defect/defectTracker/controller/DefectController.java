package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/defect")
@RequiredArgsConstructor

public class DefectController {
    @Autowired
    private DefectService defectService;
    private DefectService DefectService;

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getDefectByDefectId(@PathVariable String id) {
        Defect defect = DefectService.getDefectByDefectId(id);
        if (defect != null) {
            return ResponseEntity.ok(
                    new StandardResponse("Success", "Retrieved successfully", defect, 200)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new StandardResponse("Error", "Defect not found", null, 404));
    }

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
    @GetMapping("/assignee/{userId}")
    public ResponseEntity<List<Defect>> getDefectsByAssignee(@PathVariable Long userId) {
        List<Defect> defects = DefectService.getDefectsByAssignee(userId);
        return ResponseEntity.ok(defects);
    }
}



