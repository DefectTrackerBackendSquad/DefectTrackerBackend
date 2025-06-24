package com.defect.defectTracker.controller;

import com.defect.defectTracker.dto.DefectDto;
import com.defect.defectTracker.entity.Defect;
import com.defect.defectTracker.service.DefectService;
import com.defect.defectTracker.utils.StandardResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/defect")
@RequiredArgsConstructor
public class DefectController {

    @Autowired
    private DefectService defectService;

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getDefectByDefectId(@PathVariable String id) {
        Defect defect = defectService.getDefectByDefectId(id);
        if (defect != null) {
            return ResponseEntity.ok(new StandardResponse("Success", "Retrieved successfully", defect, 200));
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, Object>() {{
                put("status", "Failure");
                put("message", "Bad Request");
                put("statusCode", 4000);
            }});
        }
    }

    @GetMapping("/assignee/{userId}")
    public ResponseEntity<List<Defect>> getDefectsByAssignee(@PathVariable Long userId) {
        List<Defect> defects = defectService.getDefectsByAssignee(userId);
        return ResponseEntity.ok(defects);
    }

    @GetMapping("/testcase/{testcaseId}")
    public ResponseEntity<ApiResponse> getDefectByTestcaseId(@PathVariable String testcaseId) {
        try {
            DefectDto dto = defectService.getDefectByTestcaseId(testcaseId);
            return ResponseEntity.ok(new ApiResponse(20000, "Retrieved Successfully", dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(40000, ex.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateDefect(@PathVariable String id, @RequestBody DefectDto defectDTO) {

        // Validate description
        if (defectDTO.getDescription() == null || defectDTO.getDescription().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse("error", "Description cannot be null or empty", null, HttpStatus.BAD_REQUEST.value()));
        }

        // Get existing defect
        Defect existing = defectService.getDefectByDefectId(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new StandardResponse("error", "Defect not found", null, HttpStatus.NOT_FOUND.value()));
        }

        // Update fields
        existing.setDescription(defectDTO.getDescription());
        existing.setSteps(defectDTO.getSteps());
        existing.setReOpenCount(defectDTO.getReOpenCount());
        existing.setAttachment(defectDTO.getAttachment());

        try {
            Defect updated = defectService.updateDefect(existing);
            return ResponseEntity.ok(new StandardResponse("success", "Defect updated successfully", updated, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new StandardResponse("error", "Failed to update defect: " + e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @Data
    @AllArgsConstructor
    static class ApiResponse {
        private int statusCode;
        private String message;
        private Object data;
    }
}
