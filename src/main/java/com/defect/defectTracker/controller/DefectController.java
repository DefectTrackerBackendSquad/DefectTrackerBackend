package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDTO;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/defects")
@RequiredArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @PostMapping
    public ResponseEntity<StandardResponse> createDefect(@RequestBody DefectDTO defectRequest) {
        StandardResponse response = defectService.createDefect(defectRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/assignTo/{userId}")
    public ResponseEntity<List<DefectDTO>> getDefectsByAssignee(
            @PathVariable String userId) {
        return ResponseEntity.ok(defectService.getDefectsByAssignee(userId));
    }
}