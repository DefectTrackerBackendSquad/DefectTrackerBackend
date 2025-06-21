package com.defect.defectTracker.controller;

import com.defect.defectTracker.entity.DefectStatus;
import com.defect.defectTracker.repository.DefectStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/defectStatus")
@RequiredArgsConstructor
public class DefectStatusController {

    private final DefectStatusRepo repo;

    @GetMapping
    public ResponseEntity<?> getAllDefectStatuses() {
        List<DefectStatus> statuses = repo.findAll();

        if (statuses == null || statuses.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "statusCode", "10009",
                    "message", "No Defect Statuses found.",
                    "data", List.of()
            ));
        }

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "statusCode", "10001",
                "message", "Defect Status list fetched successfully.",
                "data", statuses
        ));
    }
}
