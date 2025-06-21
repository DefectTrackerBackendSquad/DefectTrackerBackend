package com.defect.defectTracker.controller;

import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.service.DefectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/defects")
@RequiredArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getDefectsByProjectId(@PathVariable String projectId) {
        List<Defect> defects = defectService.getDefectsByProjectId(projectId);

        if (defects == null || defects.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "statusCode", "10011",
                    "message", "No defects found for the given project ID.",
                    "data", List.of()
            ));
        }

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "statusCode", "10002",
                "message", "Defect list fetched successfully.",
                "data", defects
        ));
    }
}
